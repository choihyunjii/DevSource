package com.example.datahub_back.service.treeService.history

import com.example.datahub_back.dto.toolDTO.Project
import com.example.datahub_back.dto.treeDTO.ChangeColumn
import com.example.datahub_back.dto.treeDTO.ChangeData
import com.example.datahub_back.dto.treeDTO.ChangeTable
import com.example.datahub_back.dto.treeDTO.Commit
import com.example.datahub_back.service.backDataService.ProjectService
import com.example.datahub_back.service.treeService.BranchService
import com.example.datahub_back.service.treeService.CommitService
import com.example.datahub_back.service.treeService.tableColumnData.ChangeColumnService
import com.example.datahub_back.service.treeService.tableColumnData.ChangeDataService
import com.example.datahub_back.service.treeService.tableColumnData.ChangeTableService
import org.springframework.stereotype.Service

@Service
class HistoryService (
    private val projectService: ProjectService,
    private val branchService: BranchService,
    private val commitService: CommitService,
    private val changeTableService: ChangeTableService,
    private val changeColumnService: ChangeColumnService,
    private val changeDataService: ChangeDataService
) {
    fun retrieveProjects(userId: Long): List<Project> =
        projectService.getProjectsByTeamProfile(userId)

    fun retrieveCommits(projectId: Long): List<Commit> {
        val branch = branchService.getBranchByProjectId(projectId)
        if (branch != null)
            return commitService.getCommitsByBranch(branch)
        return emptyList()
    }

    fun retrieveChangeTables(commitId: Int): List<ChangeTable>? {
        val commit = commitService.getCommitByCommitId(commitId)
        return commit?.let { changeTableService.getTablesByCommit(it) }
    }

    fun retrieveChangeColumnAndData(tableId: Long): MutableMap<String, List<ChangeData>> {
        val columns = getChangeColumnsByTableId(tableId)

        val tableMap: MutableMap<String, List<ChangeData>> = mutableMapOf()

        columns?.forEach { column ->
            val data = getChangeDataByColumn(column.columnId)
            if (data != null) {
                val sortedData = sortColumnLine(data) //데이터 정렬 후
                tableMap[column.columnName] = sortedData
            }
        }
        return tableMap
    }

    private fun getChangeColumnsByTableId(tableId: Long): List<ChangeColumn>? {
        val table = changeTableService.getTableById(tableId)
        return table?.let { changeColumnService.getColumnsByTable(it) }
    }

    private fun getChangeDataByColumn(columnId: Long): List<ChangeData>? {
        val column = changeColumnService.getColumnByColumId(columnId)
        return column?.let { changeDataService.getDataListByColumn(it) }
    }

    private fun sortColumnLine(data: List<ChangeData>): List<ChangeData> =
        data.sortedBy { it.columnLine }
}