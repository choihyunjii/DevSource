package com.example.datahub_back.service.treeService.commit

import com.example.datahub_back.dto.toolDTO.Project
import com.example.datahub_back.dto.treeDTO.*
import com.example.datahub_back.service.treeService.CommitService
import com.example.datahub_back.service.treeService.sourceTable.SourceColumnService
import com.example.datahub_back.service.treeService.sourceTable.SourceDataService
import com.example.datahub_back.service.treeService.sourceTable.SourceTableService
import org.springframework.stereotype.Service

@Service
class AddChangeService (
    private val commitService: CommitService,
    private val sourceTableService: SourceTableService,
    private val sourceColumnService: SourceColumnService,
    private val sourceDataService: SourceDataService,
    private val dataTransformationService: DataTransformationService,
    private val addChangesAsTableService: AddChangesAsTableService,
)
{
    fun addChanges(branch: Branch, newCommit: Commit, project: Project) {
        val latestCommit = commitService.getLatestCommitByBranch(branch) // 최신커밋
        val oldTables = latestCommit?.let { sourceTableService.getTablesByCommit(it) }
        // tool -> tree 형으로 바꾸기
        val toolAllData = dataTransformationService.executeChangeOperations(project, newCommit)
        if (toolAllData != null) {
            val (newTables, newColumns, newData) = toolAllData
            // source 형으로 데이터 넣기
            addSourceTables(newTables)
            addSourceColumns(newColumns)
            addSourceDataItems(newData)
            // 테이블 기준 : 새로 생성된 데이터 찾아서 change 형으로 넣기
            addChangesAsTableService.addChangesAsTable(oldTables, newTables, newCommit)
            // 데이터 기준 : 새로 생성된 데이터 찾아서 change 형으로 넣기
        }
    }

    private fun addSourceTables(sourceTables: List<SourceTable>) {
        sourceTables.forEach { sourceTableService.createTable(it) }
    }

    private fun addSourceColumns(columns: List<SourceColumn>) {
        columns.forEach { sourceColumnService.createColumn(it) }
    }

    private fun addSourceDataItems(dataItems: List<SourceData>) {
        dataItems.forEach { sourceDataService.createData(it) }
    }
}

//    [변경 사항 찾고 저장하기 시작]
//    3. 커밋들을 모두 찾아서 가장 최신 커밋을 찾음
//    4. 그 커밋의 모든 테이블을 찾아옴
//    5. Tool -> tree 변환 후 데이터(테이블, 행, 값) 모두 뽑아옴
//    5-5. source 형으로 데이터 모두 추가
//    6. 새로 생성된 테이블 찾기에 각각의 테이블 리스트 전달
//    [변경 사항 찾고 저장하기 끝난 후]
//    1. Commit 객체로 Tool 테이블의 데이터 자체들을 모두 SourceTable, SourceColumn, SourceData로 저장