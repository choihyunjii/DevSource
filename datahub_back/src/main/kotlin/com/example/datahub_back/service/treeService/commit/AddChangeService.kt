package com.example.datahub_back.service.treeService.commit

import com.example.datahub_back.dto.toolDTO.Project
import com.example.datahub_back.dto.treeDTO.*
import com.example.datahub_back.service.treeService.CommitService
import com.example.datahub_back.service.treeService.tableColumnData.SourceColumnService
import com.example.datahub_back.service.treeService.tableColumnData.SourceDataService
import com.example.datahub_back.service.treeService.tableColumnData.SourceTableService
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
    fun addChanges(newCommit: Commit) {
        val latestCommit = commitService.getSecondLatestCommitByBranch(newCommit.branch) // newCommit 이전 커밋
        val oldTables = latestCommit?.let { sourceTableService.getTablesByCommit(it) }
        // tool -> tree 형으로 바꾸기
        val toolAllData = dataTransformationService.executeChangeOperations(newCommit.branch.project, newCommit)
        toolAllData?.let { (newTables, newColumns, newData) ->
            // source 형으로 데이터 넣기
            addSourceTables(newTables)
            addSourceColumns(newColumns)
            addSourceDataItems(newData)

            // 테이블 기준 : 새로 생성된 데이터 찾아서 change 형으로 넣기
            addChangesAsTableService.addChangesAsTable(oldTables, newTables, newCommit)
            // 데이터 기준 : 새로 생성된 데이터 찾아서 change 형으로 넣기

        } ?: throw IllegalStateException("Failed to execute change operations")
    }

    private fun addSourceTables(sourceTables: List<SourceTable>) {
        sourceTables.forEach { sourceTableService.createTable(it) }
    }

    private fun addSourceColumns(sourceColumns: List<SourceColumn>) {
        sourceColumns.forEach { sourceColumnService.createColumn(it) }
    }

    private fun addSourceDataItems(sourceDataItems: List<SourceData>) {
        sourceDataItems.forEach { sourceDataService.createData(it) }
    }
}

//    [변경 사항 찾고 저장하기 시작]
//    3. 커밋들을 모두 찾아서 가장 최신 커밋을 찾음
//    4. 그 커밋의 모든 테이블을 찾아옴
//    5. Tool -> tree 변환 후 데이터(테이블, 행, 값) 모두 뽑아옴
//    6. source 형으로 데이터 모두 추가
//    7. 새로 생성된 테이블 찾기에 각각의 테이블 리스트 전달