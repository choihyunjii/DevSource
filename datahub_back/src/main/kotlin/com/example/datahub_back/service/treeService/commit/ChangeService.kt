package com.example.datahub_back.service.treeService.commit

import com.example.datahub_back.dto.treeDTO.*
import com.example.datahub_back.service.treeService.CommitService
import com.example.datahub_back.service.treeService.tableColumnData.*
import org.springframework.stereotype.Service

@Service
class ChangeService (
    private val commitService: CommitService,
    private val sourceTableService: SourceTableService,
    private val sourceColumnService: SourceColumnService,
    private val sourceDataService: SourceDataService,
    private val dataTransformationService: DataTransformationService,
    private val findChangesAsTableService: FindChangesAsTableService,
    private val changeTableService: ChangeTableService,
    private val changeColumnService: ChangeColumnService,
    private val changeDataService: ChangeDataService
)
{
    // 변경 사항 찾아서 리스트로 반환
    fun findChanges(newCommit: Commit) : Triple<List<SourceTable>, List<SourceColumn>, List<SourceData>>? {
        val latestCommit = commitService.getSecondLatestCommitByBranch(newCommit.branch) // newCommit 이전 커밋
        val oldTables = latestCommit?.let { sourceTableService.getTablesByCommit(it) }
        // tool -> tree 형으로 바꾸기
        val toolAllData = dataTransformationService.executeChangeOperations(newCommit.branch.project, newCommit)

        toolAllData?.let { (newTables, newColumns, newData) ->
            if (oldTables != null && newTables != null) {
                // 삭제되지 않은 테이블
                val oldTablesNotDelete = sourceTableService.getTablesByIsDelete(oldTables, 0)
                val newTablesNotDelete = sourceTableService.getTablesByIsDelete(newTables, 0)
                // 삭제된 테이블
                val oldTablesDelete = sourceTableService.getTablesByIsDelete(oldTables, 1)
                val newTablesDelete = sourceTableService.getTablesByIsDelete(newTables, 1)
                // 테이블 기준 : 새로 생성된 테이블 찾아서 change 형으로 넣기
                val resultADD1 = findChangesAsTableService.findChangesAsTable(
                    oldTablesNotDelete, newTablesNotDelete, newColumns, newData, newCommit, true)
                val resultDelete1 = findChangesAsTableService.findChangesAsTable(
                    oldTablesDelete, newTablesDelete, newColumns, newData, newCommit, false)

                // 데이터 기준 : 새로 생성된 데이터 찾아서 change 형으로 넣기
                val  resultADD2 = findChangesAsTableService.findChangesAsData(
                    oldTablesNotDelete, newTablesNotDelete, newColumns, newData, newCommit, true)
            }
        }
        return null
    }

    fun saveChangesData(result: Triple<List<ChangeTable>, List<ChangeColumn>, List<ChangeData>>) {
        val (changeTable, changeColumn, changeData) = result
        changeTable.forEach { changeTableService.createTable(it) }
        changeColumn.forEach { changeColumnService.createColumn(it) }
        changeData.forEach { changeDataService.createData(it) }
    }

    fun saveSourceData(result: Triple<List<SourceTable>, List<SourceColumn>, List<SourceData>>) {
        val (sourceTables, sourceColumns, sourceData) = result
        sourceTables.forEach { sourceTableService.createTable(it) }
        sourceColumns.forEach { sourceColumnService.createColumn(it) }
        sourceData.forEach { sourceDataService.createData(it) }
    }
}

//    [변경 사항 찾고 저장하기 시작]
//    3. 커밋들을 모두 찾아서 가장 최신 커밋을 찾음
//    4. 그 커밋의 모든 테이블을 찾아옴
//    5. Tool -> tree 변환 후 데이터(테이블, 행, 값) 모두 뽑아옴
//    6. source 형으로 데이터 모두 추가
//    7. 새로 생성된 테이블 찾기에 각각의 테이블 리스트 전달

//fun addChangesSave(newCommit: Commit) {
//    val latestCommit = commitService.getSecondLatestCommitByBranch(newCommit.branch) // newCommit 이전 커밋
//    val oldTables = latestCommit?.let { sourceTableService.getTablesByCommit(it) }
//    // tool -> tree 형으로 바꾸기
//    val toolAllData = dataTransformationService.executeChangeOperations(newCommit.branch.project, newCommit)
//    toolAllData?.let { (newTables, newColumns, newData) ->
//        // source 형으로 데이터 넣기
//        addSourceTables(newTables)
//        addSourceColumns(newColumns)
//        addSourceDataItems(newData)
//
//        if (oldTables != null && newTables != null) {
//            val oldTablesNotDelete = sourceTableService.getTablesByIsDelete(oldTables, 0)
//            val newTablesNotDelete = sourceTableService.getTablesByIsDelete(newTables, 0)
//            // 테이블 기준 : 새로 생성된 테이블 찾아서 change 형으로 넣기
//            findChangesAsTableService.addChangesAsTable(oldTablesNotDelete, newTablesNotDelete, newCommit)
//            // 데이터 기준 : 새로 생성된 데이터 찾아서 change 형으로 넣기
//        }
//    } ?: throw IllegalStateException("Failed to execute change operations")
//}
