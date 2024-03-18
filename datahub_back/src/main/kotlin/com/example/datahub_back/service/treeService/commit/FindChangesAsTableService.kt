package com.example.datahub_back.service.treeService.commit

import com.example.datahub_back.dto.treeDTO.*
import com.example.datahub_back.service.treeService.commit.DataTransformer.Companion.toChangeColumn
import com.example.datahub_back.service.treeService.commit.DataTransformer.Companion.toChangeData
import com.example.datahub_back.service.treeService.commit.DataTransformer.Companion.toChangeTable
import com.example.datahub_back.service.treeService.tableColumnData.*
import org.springframework.stereotype.Service

@Service
class FindChangesAsTableService (
    private val sourceColumnService: SourceColumnService,
    private val sourceDataService: SourceDataService,
) {
    companion object {
        const val ADDED_CHANGE_TYPE = 1
        const val DELETED_CHANGE_TYPE = 0
    }

    // 테이블 기준으로 변경 사항 뽑기
    fun findChangesAsTable(
        oldTables: List<SourceTable>, // 이전 테이블 목록
        newTables: List<SourceTable>, // 새로운 테이블 목록
        newColumns: List<SourceColumn>, // 새로운 열 목록
        newData: List<SourceData>, // 새로운 데이터 목록
        newCommit: Commit, // 새로운 커밋
        isAddOperation: Boolean // 추가 작업 여부
    ): Triple<List<ChangeTable>, List<ChangeColumn>, List<ChangeData>>? {
        val changeTables = mutableListOf<ChangeTable>() // 변경된 테이블 목록
        val changeColumns = mutableListOf<ChangeColumn>() // 변경된 열 목록
        val changeData = mutableListOf<ChangeData>() // 변경된 데이터 목록

        val oldTablesNameSet = oldTables.map { it.tableName }.toSet() // 이전 테이블 이름 집합
        val newTablesList = if (isAddOperation) newTables.filter { it.tableName !in oldTablesNameSet } // 추가 작업인 경우 새로운 테이블만 선택
        else oldTables.filter { it.tableName !in newTables.map { it.tableName }.toSet() } // 삭제 작업인 경우 이전 테이블만 선택

        newTablesList.forEach { table ->
            changeTables.add(table.toChangeTable(newCommit)) // 변경된 테이블 추가
            val newColumnsList = getColumnsByTable(newColumns, table) // 해당 테이블에 대한 새로운 열 목록 가져오기
            newColumnsList.forEach { column ->
                changeColumns.add(column.toChangeColumn(newCommit)) // 변경된 열 추가
                val newDataList = getDataListByColumn(newData, column) // 해당 열에 대한 새로운 데이터 목록 가져오기
                newDataList.forEach { data ->
                    val changeType = if (isAddOperation) ADDED_CHANGE_TYPE else DELETED_CHANGE_TYPE // 변경 유형 지정
                    changeData.add(data.toChangeData(newCommit, changeType)) // 변경된 데이터 추가
                }
            }
        }
        return Triple(changeTables, changeColumns, changeData) // 변경된 테이블, 열, 데이터 목록을 Triple로 반환
    }

    // 데이터 기준으로 변경 사항 뽑기
    fun findChangesAsData(
        oldTableList: List<SourceTable>, // 이전 테이블 목록
        newTableList: List<SourceTable>, // 새로운 테이블 목록
        newColumnList: List<SourceColumn>, // 새로운 열 목록
        newDataList: List<SourceData>, // 새로운 데이터 목록
        newCommit: Commit, // 새로운 커밋
        isAddOperation: Boolean // 추가 작업 여부
    ): Triple<List<ChangeTable>, List<ChangeColumn>, List<ChangeData>>? {
        val changeTables = mutableListOf<ChangeTable>() // 변경된 테이블 목록
        val changeColumns = mutableListOf<ChangeColumn>() // 변경된 열 목록
        val changeData = mutableListOf<ChangeData>() // 변경된 데이터 목록

        oldTableList.forEach { oldTable ->
            val oldColumns = sourceColumnService.getColumnsByTable(oldTable) // 이전 테이블의 열 목록 가져오기
            val oldData = sourceDataService.getDataListByColumns(oldColumns) // 이전 테이블의 데이터 목록 가져오기

            val relevantNewTable = getTableByTableName(newTableList, oldTable.tableName) // 이전 테이블과 이름이 같은 테이블 가져오기
            val relevantNewColumns = relevantNewTable?.let { getColumnsByTable(newColumnList, it) } // 이전 테이블에 해당하는 새로운 열 목록 가져오기
            val relevantNewData = relevantNewColumns?.let { getDataListByColumns(newDataList, it) } // 이전 테이블에 해당하는 새로운 데이터 목록 가져오기

            val oldDataValue = oldData.map { it.data } // 이전 데이터 값 목록
            val newAddData = relevantNewData?.filterNot { new -> new.data in oldDataValue } // 이전 데이터와 중복되지 않는 새로운 데이터 목록 가져오기
            newAddData?.forEach { new ->
                val changeType = if (isAddOperation) ADDED_CHANGE_TYPE else DELETED_CHANGE_TYPE // 변경 유형 지정
                val newDataToChangeData = new.toChangeData(newCommit, changeType) // 새로운 데이터를 변경된 데이터로 변환
                if (!changeData.contains(newDataToChangeData)) { // 변경된 데이터 목록에 없는 경우에만 추가
                    val lineNewDataList = findDataListByColumnAndLine(newDataList, relevantNewColumns, new.columnLine) // 해당 라인의 데이터 목록 가져오기
                    lineNewDataList?.forEach { data ->
                        changeData.add(data.toChangeData(newCommit, changeType)) // 변경된 데이터 추가
                    }
                }
                relevantNewColumns?.forEach { newColumn ->
                    if (!changeColumns.contains(newColumn.toChangeColumn(newCommit))) { // 변경된 열 목록에 없는 경우에만 추가
                        changeColumns.add(newColumn.toChangeColumn(newCommit)) // 변경된 열 추가
                    }
                }
                if (!changeTables.contains(relevantNewTable.toChangeTable(newCommit))) { // 변경된 테이블 목록에 없는 경우에만 추가
                    changeTables.add(relevantNewTable.toChangeTable(newCommit)) // 변경된 테이블 추가
                }
            }
        }
        return Triple(changeTables, changeColumns, changeData) // 변경된 테이블, 열, 데이터 목록을 Triple로 반환
    }

    private fun getTableByTableName(tableList: List<SourceTable>, tableName: String) =
        tableList.find { it.tableName == tableName }

    private fun getColumnsByTable(columnList: List<SourceColumn>, table: SourceTable) =
        columnList.filter { it.table == table }

    private fun getDataListByColumns(columnList: List<SourceData>, columns: List<SourceColumn>) =
        columnList.filter { data ->
            columns.any { column -> data.column == column }
        }

    private fun getDataListByColumn(dataList: List<SourceData>, column: SourceColumn) =
        dataList.filter { it.column == column }

    fun findDataListByColumnAndLine(dataList: List<SourceData>, column: List<SourceColumn>, columnLine: Int): List<SourceData>? {
        return dataList.filter { it.column in column && it.columnLine == columnLine }
    }
}


//    [새로 생성된 테이블 찾기]
//    0. newTables와 oldTables의 테이블 중 삭제되지 않은 테이블 뽑아서 리스트 저장
//    1. 테이블 이름으로 차집합을 찾아서 비교
//    2. 새로 생성된 테이블 찾아서 ChangeTable 생성 및 저장 (테이블 자체가 새로 생성된거니 컬럼과 데이터 비교 X)
//    3. 그 리스트로 반복문 시작
//    4. 그 테이블 리스트로 찾은 ChangeColumn을 그대로 생성 및 저장
//    6. 생성한 리스트로 반복문 시작
//    7. 그 컬럼 리스트로 찾은 ChangeData을 그대로 생성 및 저장

//    [테이블끼리 비교해서 새로 추가된 행 데이터 찾기] - 기존 로직 참고
//    3. 기존 테이블의 행을 모두 찾아 리스트에 각각 넣음
//    4. 그 리스트로 반복문 시작
//    9. 그 데이터의 columLine을 가지고 있고,
//    해당 column 객체를 가지고 있는 데이터를 모두 찾아 ChangeData에 데이터 저장
//    10. 그리고 역 추적으로  ChangeTable, ChangeColumn에 데이터 저장하되,
//    11. ChangeTable, ChangeColumn가 중복으로 저장 되어 있는지 확인 후 저장

//fun addChangesAsTable(oldTablesNotDelete: List<SourceTable>,
//                      newTablesNotDelete: List<SourceTable>,
//                      newCommit: Commit
//) {
//    // 이름으로 비교해서 새로 생성된 테이블들 찾기
//    val oldTablesNameSet = oldTablesNotDelete.map { it.tableName }.toSet()
//    val newAddTables = newTablesNotDelete.filter {
//        it.tableName !in oldTablesNameSet
//    }
//    println("새로 생성된 테이블 : $newAddTables")
//
//    // 새로 생성된 테이블 데이터들 Change형으로 바꿔서 생성
//    newAddTables.forEach { table ->
//        changeTableService.createTable(table.toChangeTable(newCommit))
//        val newAddColumns = sourceColumnService.getColumnsByTable(table)
//        newAddColumns.forEach { column ->
//            changeColumnService.createColumn(column.toChangeColumn(newCommit))
//            val newAddData = sourceDataService.getDataListByColumn(column)
//            newAddData.forEach { data ->
//                changeDataService.createData(data.toChangeData(newCommit, 1))
//            } // forEach
//        } // forEach
//    } // forEach
//} // fun