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

    private val changeTables = mutableListOf<ChangeTable>() // 변경된 테이블 목록
    private val changeColumns = mutableListOf<ChangeColumn>() // 변경된 열 목록
    private val changeData = mutableListOf<ChangeData>() // 변경된 데이터 목록

    private val oldTableList = mutableListOf<SourceTable>()
    private val newTableList = mutableListOf<SourceTable>()
    private val newColumnList = mutableListOf<SourceColumn>()
    private val newDataList = mutableListOf<SourceData>()

    fun findChanges (
        oldTables: List<SourceTable>, // 이전 테이블 목록
        newTables: List<SourceTable>, // 새로운 테이블 목록
        newColumns: List<SourceColumn>, // 새로운 열 목록
        newData: List<SourceData>, // 새로운 데이터 목록
        newCommit: Commit, // 새로운 커밋
        isAddOperation: Boolean // 추가 작업 여부
    ): Triple<List<ChangeTable>, List<ChangeColumn>, List<ChangeData>>? {

        // 리스트 초기화
        oldTableList.clear()
        newTableList.clear()
        newColumnList.clear()
        newDataList.clear()

        // 새로운 데이터 추가
        oldTableList.addAll(oldTables)
        newTableList.addAll(newTables)
        newColumnList.addAll(newColumns)
        newDataList.addAll(newData)

        findChangesAsTable(newCommit, isAddOperation)
        if (isAddOperation) {
            findChangesAsData(newCommit)
        }
        return Triple(changeTables, changeColumns, changeData)
    }

    fun clearChangeList() {
        changeTables.clear()
        changeColumns.clear()
        changeData.clear()
    }

    // 테이블 기준으로 변경 사항 뽑기
    private fun findChangesAsTable(
        newCommit: Commit, // 새로운 커밋
        isAddOperation: Boolean // 추가 작업 여부
    ) {
        val oldTablesNameSet = oldTableList.map { it.tableName }.toSet() // 이전 테이블 이름 집합
        val newTablesNameSet = newTableList.map { it.tableName }.toSet()
        val newTablesList = if (isAddOperation) newTableList.filter { it.tableName !in oldTablesNameSet } // 추가 작업인 경우 새로운 테이블만 선택
        else oldTableList.filter { it.tableName !in newTablesNameSet } // 삭제 작업인 경우 이전 테이블만 선택

        newTablesList.forEach { table ->
            changeTables.add(table.toChangeTable(newCommit)) // 추가된 테이블 추가
            val newColumnsList = getColumnsByTable(newColumnList, table) // 해당 테이블에 대한 새로운 열 목록 가져오기
            newColumnsList.forEach { column ->
                changeColumns.add(column.toChangeColumn(newCommit)) // 추가된 열 추가
                val newDataList = getDataListByColumn(newDataList, column) // 해당 열에 대한 새로운 데이터 목록 가져오기
                newDataList.forEach { data ->
                    val changeType = if (isAddOperation) ADDED_CHANGE_TYPE else DELETED_CHANGE_TYPE // 변경 유형 지정
                    changeData.add(data.toChangeData(newCommit, changeType)) // 추가된 데이터 추가
                }
            }
        }
    }

    // 데이터 기준으로 변경 사항 뽑기
    private fun findChangesAsData(
        newCommit: Commit, // 새로운 커밋
    ) {
        // 테이블 이름이 같은 것을 찾아서 매핑
        val commonTables = mutableListOf<Pair<SourceTable, SourceTable>>()
        for (newTable in newTableList) {
            val oldTable = oldTableList.find { it.tableName == newTable.tableName }
            oldTable?.let { commonTables.add(Pair(newTable, oldTable)) }
        }

        // 같은 이름을 가진 테이블 끼리 비교 시작
        commonTables.forEach { (newTable, oldTable) ->
            // 열 목록 가져오기
            val newColumns = newColumnList.filter { it.table == newTable }
            val oldColumns = sourceColumnService.getColumnsByTable(oldTable)
            val sortedNewColumns = newColumns.sortedBy { it.columnId }
            val sortedOldColumns = oldColumns.sortedBy { it.columnId }

            var addTableColumnAction = false

            // 행이 추가 or 삭제 됐다면 모든 데이터 change 저장
            if (sortedNewColumns.size != sortedOldColumns.size) {
                addTableColumnAction = true
                val allNewData = getDataListByColumns(newDataList, newColumns)
                allNewData.forEach { new ->
                    if (!changeData.contains(new.toChangeData(newCommit, 1))) { // 변경된 데이터 목록에 없는 경우에만 추가
                        val lineNewDataList = findDataListByColumnAndLine(newDataList, newColumns, new.columnLine) // 해당 라인의 데이터 목록 가져오기
                        print("행 추가, 삭제로 인한 데이터 추가 : ")
                        lineNewDataList?.forEach { data ->
                            changeData.add(data.toChangeData(newCommit, 1)) // 변경된 데이터 추가
                            print("${data.data}, ")
                        }
                    }
                }
                val allOldData = sourceDataService.getDataListByColumns(oldColumns)
                allOldData.forEach { old ->
                    if (!changeData.contains(old.toChangeData(newCommit, 0))) {
                        val lineNewDataList = sourceDataService.findDataListByColumnAndLine(newColumns, old.columnLine)
                        lineNewDataList?.forEach { data ->
                            changeData.add(data.toChangeData(newCommit, 0)) // 변경된 데이터 추가
                        }
                    }
                }
            } else { // 아니면 비교 시작
                val columnMapping = mutableMapOf<SourceColumn?, SourceColumn?>()
                for (i in sortedNewColumns.indices) {
                    columnMapping[sortedNewColumns[i]] = sortedOldColumns[i]
                }

                // 같은 위치의 행끼리 비교 시작
                columnMapping.forEach { (newColumn, oldColumn) ->
                    if (newColumn != null && oldColumn != null) {
                        val newData = newDataList.filter { it.column == newColumn }
                        val oldData = sourceDataService.getDataListByColumn(oldColumn)
                        val sortedNewData = newData.sortedBy { it.columnLine }
                        val sortedOldData = oldData.sortedBy { it.columnLine }

                        val dataMapping = mutableMapOf<SourceData?, SourceData?>()
                        val minSize = minOf(sortedNewData.size, sortedOldData.size)
                        for (i in 0 until minSize) {
                            dataMapping[sortedNewData[i]] = sortedOldData[i]
                        }
                        // 더 긴 리스트의 나머지 요소들은 null로 매핑
                        for (i in minSize until sortedNewData.size) {
                            dataMapping[sortedNewData[i]] = null
                        }
                        for (i in minSize until sortedOldData.size) {
                            dataMapping[null] = sortedOldData[i]
                        }

                        // 같은 위치의 데이터끼리 비교 시작
                        dataMapping.forEach { (new, old) ->
                            if (new == null && old != null) { // 삭제된 데이터라면
                                println("삭제된 데이터 : ${old.data}")
                                addTableColumnAction = true
                                if (!changeData.contains(old.toChangeData(newCommit, 0))) { // 변경된 데이터 목록에 없는 경우에만 추가
                                    val lineDataList = sourceDataService.findDataListByColumnAndLine(oldColumns, old.columnLine) // 해당 라인의 데이터 목록 가져오기
                                    lineDataList?.forEach { data ->
                                        changeData.add(data.toChangeData(newCommit, 0)) // 변경된 데이터 추가
                                    }
                                }
                            } else if (old == null && new != null) { // 추가된 데이터라면
                                println("추가된 데이터 : ${new.data}")
                                addTableColumnAction = true
                                if (!changeData.contains(new.toChangeData(newCommit, 1))) { // 변경된 데이터 목록에 없는 경우에만 추가
                                    val lineDataList = findDataListByColumnAndLine(newDataList, newColumns, new.columnLine) // 해당 라인의 데이터 목록 가져오기
                                    lineDataList?.forEach { data ->
                                        changeData.add(data.toChangeData(newCommit, 1)) // 변경된 데이터 추가
                                    }
                                }
                            } else if (new?.data != old?.data && new != null && old != null) { // 데이터 값이 같지 않다면 (수정)
                                println("수정된 데이터 : ${old.data} -> ${new.data}")
                                addTableColumnAction = true
                                if (!changeData.contains(new.toChangeData(newCommit, 1))) { // 변경된 데이터 목록에 없는 경우에만 추가
                                    val lineDataList = findDataListByColumnAndLine(newDataList, newColumns, new.columnLine) // 해당 라인의 데이터 목록 가져오기
                                    lineDataList?.forEach { data ->
                                        changeData.add(data.toChangeData(newCommit, 1)) // 변경된 데이터 추가
                                    }
                                }
                                if (!changeData.contains(old.toChangeData(newCommit, 0))) { // 변경된 데이터 목록에 없는 경우에만 추가
                                    val lineDataList = sourceDataService.findDataListByColumnAndLine(oldColumns, old.columnLine) // 해당 라인의 데이터 목록 가져오기
                                    lineDataList?.forEach { data ->
                                        changeData.add(data.toChangeData(newCommit, 0)) // 변경된 데이터 추가
                                    }
                                }
                            } // else if
                        } // forEach
                    } // forEach
                } // forEach
            } // else
            if (addTableColumnAction) {
                if (!changeTables.contains(newTable.toChangeTable(newCommit))) { // 변경된 테이블 목록에 없는 경우에만 추가
                    changeTables.add(newTable.toChangeTable(newCommit)) // 변경된 테이블 추가
                }
                newColumns.forEach { newColumn ->
                    if (!changeColumns.contains(newColumn.toChangeColumn(newCommit))) { // 변경된 열 목록에 없는 경우에만 추가
                        changeColumns.add(newColumn.toChangeColumn(newCommit)) // 변경된 열 추가
                    }
                }
            }
        } // forEach
    } // fun

    private fun getColumnsByTable(columnList: List<SourceColumn>, table: SourceTable) =
        columnList.filter { it.table == table }

    private fun getDataListByColumns(dataList: List<SourceData>, columns: List<SourceColumn>) =
        dataList.filter { data -> columns.any { column -> data.column == column } }

    private fun getDataListByColumn(dataList: List<SourceData>, column: SourceColumn) =
        dataList.filter { it.column == column }

    private fun findDataListByColumnAndLine(dataList: List<SourceData>, column: List<SourceColumn>, columnLine: Int): List<SourceData>? {
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