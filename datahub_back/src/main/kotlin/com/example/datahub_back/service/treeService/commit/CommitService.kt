package com.example.datahub_back.service.treeService.commit

import com.example.datahub_back.controller.treeController.CommitRequest
import com.example.datahub_back.dto.toolDTO.Project
import com.example.datahub_back.dto.treeDTO.*
import com.example.datahub_back.dto.treeDTO.SourceTable
import com.example.datahub_back.service.treeService.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CommitService(
    private val sourceCommitService: SourceCommitService,
    private val sourceTableService: SourceTableService,
    private val sourceColumnService: SourceColumnService,
    private val sourceDataService: SourceDataService,
    private val changePageService: ChangePageService,
    private val changeTableService: ChangeTableService,
    private val branchService: BranchService,
    private val changeTableComparisonService: ChangeTableComparisonService,
    private val dataTransformationService: DataTransformationService,
) {

    @Transactional(rollbackFor = [RuntimeException::class])
    fun handleCommit(commitData: CommitRequest): String {
        try {
            val profile = commitData.profile
            val project = commitData.project

            val commitHashCode = "${commitData.project}${commitData.profile}${LocalDateTime.now()}".hashCode()
            val branch = branchService.getBranchByUserAndProject(profile, project)
            val changeTables = changeTableComparisonService.getChangeTables(profile, project)

            val result = getNewSourceTables(project)
            result?.let { (newTables, newColumns, newData) ->
                if (branch != null) {
                    val commit = Commit(
                        commitId = commitHashCode,
                        branch = branch,
                        comment = commitData.comment,
                        createTime = LocalDateTime.now(),
                        createUser = profile.username,
                        sourceTables = newTables,
                        changeTables = changeTables,
                        changePageIds = null
                    )
                    processDataItems(newTables, newColumns, newData, changeTables, null)
                    sourceCommitService.createCommit(commit)
                    branchService.updatePushCountByBranchId(branch.branchId)
                }
            }
        } catch (e: Exception) {
            throw RuntimeException("Error occurred during commit processing: ${e.message}")
        }
        return "All commits processed successfully"
    }

    private fun getNewSourceTables(project: Project): Triple<List<SourceTable>, List<SourceColumn>, List<SourceData>>? =
        dataTransformationService.executeChangeOperations(project)


    private fun processDataItems(sourceTables: List<SourceTable>, columns: List<SourceColumn>, data: List<SourceData>, changeTables: List<ChangeTable>?, changePages: List<ChangePage>?) {
        addTables(sourceTables)
        addColumns(columns)
        addDataItems(data)
        if (changePages != null) {
            addChangePages(changePages)
        }
        if (changeTables != null) {
            addChangeTables(changeTables)
        }
    }

    private fun addTables(sourceTables: List<SourceTable>) {
        sourceTables.forEach { sourceTableService.createTable(it) }
    }

    private fun addColumns(columns: List<SourceColumn>) {
        columns.forEach { sourceColumnService.createColumn(it) }
    }

    private fun addDataItems(dataItems: List<SourceData>) {
        dataItems.forEach { sourceDataService.createData(it) }
    }

    private fun addChangePages(changePages: List<ChangePage>) {
        changePages.forEach { changePageService.createChangePage(it) }
    }

    private fun addChangeTables(changeTableColumns: List<ChangeTable>) {
        changeTableColumns.forEach { changeTableService.createChangeTable(it) }
    }

}
