//package com.example.datahub_back.service.treeService.commit
//
//import com.example.datahub_back.dto.toolDTO.Profile
//import com.example.datahub_back.dto.toolDTO.Project
//import com.example.datahub_back.dto.treeDTO.*
//import com.example.datahub_back.service.treeService.*
//import org.springframework.stereotype.Service
//
//@Service
//class ChangeTableComparisonService(
//    private val sourceCommitService: SourceCommitService,
//    private val sourceTableService: SourceTableService,
//    private val sourceColumnService: SourceColumnService,
//    private val sourceDataService: SourceDataService,
//    private val dataTransformationService: DataTransformationService,
//    private val branchService: BranchService
//) {
//    // changeTables 뽑아내기
//    fun getChangeTables(profile: Profile, project: Project): List<ChangeTable>?{
//        val branch = branchService.getBranchByUserAndProject(profile, project )
//        val latestCommit = branch?.let { getLatestCommit(it) }
//        val result = dataTransformationService.executeChangeOperations(project)
//
//        if (result != null) {
//            if (latestCommit != null) {
//                val (newTables, newColumns, newData) = result
//                val oldTables = getTables(latestCommit)
//
//                // 추가된 테이블 List<ChangeTable> 가져오기
//                val addTableChangeTables = getAddTables(newTables, newColumns, newData, oldTables)
//                val addColumnAndDataChangeTables = getAddColumnAndData(newTables, newColumns, newData, oldTables)
//
//                val changeTables = mutableListOf<ChangeTable>()
//                changeTables.addAll(addTableChangeTables)
//                changeTables.addAll(addColumnAndDataChangeTables)
//
//                return changeTables
//
//            } else { // 최초 커밋일 때
//                val (newTables, newColumns, newData) = result
//                return newTables.map { table ->
//                    ChangeTable(
//                        changeTableId = table.tableId,
//                        changeTableName = table.tableName,
//                        action = ChangeAction.ADD,
//                        primaryKey = getPrimaryKeyMap(newColumns, table.tableId),
//                        columns = getColumnsMap(newColumns, newData, table.tableId)
//                    )
//                } // map
//            } // else
//        } // if
//        return null
//    }
//
//    // 추가된 테이블 찾기
//    private fun getAddTables(newTables: List<SourceTable>, newColumns: List<SourceColumn>, newData: List<SourceData>, oldTables: List<SourceTable>): List<ChangeTable> {
//        val newActiveTables = getActiveTables(newTables, 0)
//        val oldActiveTables = getActiveTables(oldTables, 0)
//
//        // 새로운 테이블 중에서 이전에 없던 테이블을 찾음
//        val addedTables = newActiveTables.filterNot { it in oldActiveTables }
//
//        return addedTables.map { table ->
//            ChangeTable(
//                changeTableId = table.tableId,
//                changeTableName = table.tableName,
//                action = ChangeAction.ADD,
//                primaryKey = getPrimaryKeyMap(newColumns, table.tableId),
//                columns = getColumnsMap(newColumns, newData, table.tableId)
//            )
//        }
//    }
//
//    // 추가 테이블 기본키 Name 찾기
//    private fun getPrimaryKeyMap(columnList: List<SourceColumn>, tableId: Long): String? {
//        val columns = getColumnsByTableId(columnList, tableId)
//        val primaryKeyColumn = columns.find { it.isPrimaryKey == 1 }
//
//        return primaryKeyColumn?.columnName
//    }
//
//    private fun getColumnsByTableId(columnList: List<SourceColumn>, tableId: Long) : List<SourceColumn> =
//        columnList.filter { it.tableId == tableId }
//
//    // 추가 테이블 컬럼 key, value 찾기
//    private fun getColumnsMap(newColumnList: List<SourceColumn>, newDataList: List<SourceData>, tableId: Long): MutableList<ChangeTableColumn> {
//        val changeTableColumns = mutableListOf<ChangeTableColumn>()
//        val columns = newColumnList.filter { it.tableId == tableId }
//        var index = 0
//
//        columns.forEach { column ->
//            val sourceDataList = newDataList.filter { it.columnId == column.columnId }
//            index++
//            sourceDataList.forEach { data ->
//                val changeTableColumn = ChangeTableColumn(
//                    changeTableId = column.tableId,
//                    columnIndex = index,
//                    columnName = column.columnName,
//                    columnValue = data.data
//                )
//                changeTableColumns.add(changeTableColumn)
//            }
//        }
//        return changeTableColumns
//    }
//
//    // 추가된 행과 데이터 찾기
//    private fun getAddColumnAndData(newTables: List<SourceTable>, newColumnList: List<SourceColumn>, newDataList: List<SourceData>, oldTables: List<SourceTable>): List<ChangeTable> {
//        // 새로운 테이블과 이전 테이블 중에서 활성화된(삭제되지 않은) 테이블을 가져옴
//        val newActiveTables = getActiveTables(newTables, 0)
//        val oldActiveTables = getActiveTables(oldTables, 0)
//
//        return getChangeColumData(newActiveTables, newColumnList, newDataList, oldActiveTables)
//    }
//
//    // 데이터 목록 가져오기 (아이템 하나씩 가져오기)
//    private fun getDataItems(Columns: List<SourceColumn>) : List<SourceData>{
//        val sourceDataList = mutableListOf<SourceData>()
//        Columns.forEach { column ->
//            val data = sourceDataService.getDataListByColumnId(column.columnId)
//            data.forEach { item ->
//                sourceDataList.add(item)
//            }
//        }
//        return sourceDataList
//    }
//
//    // 각 columnLine 번호에 대한 데이터를 MutableList<ChangeTableColumn>로 반환
//    private fun mapColumnDataByLine(uniqueColumnLines: List<Int>, newSourceDataList: List<SourceData>, newColumns: List<SourceColumn>) : MutableList<ChangeTableColumn> {
//        val changeTableColumns = mutableListOf<ChangeTableColumn>()
//        var index = 0
//
//        uniqueColumnLines.forEach { columnLine ->
//            val dataWithColumnLine = newSourceDataList.filter { it.columnLine == columnLine }
//            index++
//            dataWithColumnLine.forEach { sourceData ->
//                val column = newColumns.find { it.columnId == sourceData.columnId }
//                requireNotNull(column) { "Column not found for source data with columnId: ${sourceData.columnId}" }
//                val changeTableColumn = ChangeTableColumn(
//                    changeTableId = column.tableId,
//                    columnIndex = index,
//                    columnName = column.columnName,
//                    columnValue = sourceData.data
//                )
//                changeTableColumns.add(changeTableColumn)
//            }
//        }
//
//        return changeTableColumns
//    }
//
//    private fun getChangeColumData(newActiveTables: List<SourceTable>, newColumnList: List<SourceColumn>, newDataList: List<SourceData>, oldActiveTables: List<SourceTable>) : List<ChangeTable>{
//        val addedColumnsAndData = mutableListOf<ChangeTable>()
//
//        // 새로운 테이블과 이전 테이블 중에서 테이블 이름이 같은 것을 찾아서 매핑
//        val commonTables = mutableListOf<Pair<SourceTable, SourceTable>>()
//        for (newTable in newActiveTables) {
//            val oldTable = oldActiveTables.find { it.tableName == newTable.tableName }
//            oldTable?.let { commonTables.add(Pair(newTable, oldTable)) }
//        }
//
//        // 같은 이름을 가진 테이블 끼리 비교 시작
//        commonTables.forEach { (newTable, oldTable) ->
//            // 열 목록 가져오기
//            val newColumns = getColumnsByTableId(newColumnList, newTable.tableId)
//            val oldColumns = sourceColumnService.getColumnsByTableId(oldTable.tableId)
//
//            // 데이터 목록 가져오기
//            val newSourceDataList = mutableListOf<SourceData>()
//            newColumns.forEach { column ->
//                val data = getDataListByColumnId(newDataList, column.columnId)
//                data.forEach { item ->
//                    newSourceDataList.add(item)
//                }
//            }
//            val oldSourceDataList = getDataItems(oldColumns)
//
//            // 차집합 데이터 찾기
//            val addedData = newSourceDataList.filterNot { it in oldSourceDataList }
//            val uniqueColumnLines = addedData.map { it.columnLine }.distinct()
//
//            val columnLineMap = mapColumnDataByLine(uniqueColumnLines, newSourceDataList, newColumns)
//
//            addedColumnsAndData.add(
//                ChangeTable(
//                    changeTableId = newTable.tableId,
//                    changeTableName = newTable.tableName,
//                    action = ChangeAction.ADD,
//                    primaryKey = getPrimaryKeyMap(newColumnList, newTable.tableId),
//                    columns = columnLineMap
//                )
//            )
//        }
//        return addedColumnsAndData
//    }
//
//    private fun getDataListByColumnId(dataList: List<SourceData>, columnId: Long) =
//        dataList.filter { it.columnId == columnId }
//
//    // 최신커밋 가져오기
//    private fun getLatestCommit(branch: Branch): Commit? =
//        sourceCommitService.getLatestCommitByBranch(branch)
//
//    // 커밋의 테이블 리스트 가져오기
//    private fun getTables(commit: Commit): List<SourceTable> {
//        return commit.sourceTables ?: emptyList()
//    }
//
//
//    // 삭제 여부로 테이블 리스트 가져오기
//    private fun getActiveTables(tables: List<SourceTable>, isDelete: Int): List<SourceTable> {
//        return tables.filter { it.isDelete == isDelete }
//    }
//}
//
//
//// -  ChangeTable DELETE 찾기 : B가 새로운 커밋
//
//// > 삭제된 테이블 찾기
//// 테이블들을 찾아서 (isDelete = 1 인것만 찾기)
//// 테이블의 차집합 꺼내기
//// 차집합을 ChangeTable로 생성
//
//// > 삭제된 행과 데이터 찾기
//// 같은 이름을 가진 테이블을 찾아 놓고 비교 시작
//// SourceColumn.columnId를 찾아냄
//// 그걸로 SourceData.columnId가 일치하는 데이터들을 뽑아와서 차집합 찾기
//// 그걸 ChangeTable로 생성