package com.example.datahub_back.service.treeService.commit

import com.example.datahub_back.dto.treeDTO.*
import com.example.datahub_back.service.treeService.*
import org.springframework.stereotype.Service

@Service
class ChangeTableComparisonService(
    private val sourceCommitService: SourceCommitService,
    private val sourceTableService: SourceTableService,
    private val sourceColumnService: SourceColumnService,
    private val sourceDataService: SourceDataService,
    private val dataTransformationService: DataTransformationService,
) {
    // 최신커밋 가져오기
    private fun getLatestCommit(commitList: List<Commit>): Commit? =
        sourceCommitService.getLatestCommitByBranch(commitList)

    // 커밋의 테이블 리스트 가져오기
    fun getTables(commit: Commit): List<SourceTable> {
        val sourceTableIds = commit.sourceTableIds
        return sourceTableService.getTableByTableIds(sourceTableIds)
    }

    // 삭제 여부로 테이블 리스트 가져오기
    private fun getActiveTables(tables: List<SourceTable>, isDelete: Int): List<SourceTable> {
        return tables.filter { it.isDelete == isDelete }
    }

    // changeTables 뽑아내기
    private fun commitComparison(commitList: List<Commit>, projectId: Long): ChangeTable?{
        val latestCommit = getLatestCommit(commitList)
        if(latestCommit != null) {
            val result = dataTransformationService.executeChangeOperations(projectId)
            if (result != null) {
                val (newTables, newColumns, newData) = result
                val oldTables = getTables(latestCommit)

            }
        } else { // 최초 커밋일 때
//            changeTableService.createChangeTable()
            return null
        }
        return null
    }


    // 추가된 테이블 찾기
    private fun getAddTable(newTables: List<SourceTable>, oldTables: List<SourceTable>): List<ChangeTable> {
        val newActiveTables = getActiveTables(newTables, 0)
        val oldActiveTables = getActiveTables(oldTables, 0)

        // 새로운 테이블 중에서 이전에 없던 테이블을 찾음
        val addedTables = newActiveTables.filterNot { it in oldActiveTables }

        return addedTables.map { table ->
            ChangeTable(
                changeTableId = table.tableId,
                changeTableName = table.tableName,
                action = ChangeAction.ADD,
                primaryKey = getPrimaryKeyMap(table.tableId),
                columns = getColumnsMap(table.tableId)
            )
        }
    }

    // 추가 테이블 기본키 Name 찾기
    private fun getPrimaryKeyMap(tableId: Long): String? {
        val columns = sourceColumnService.getColumnsByTableId(tableId)
        val primaryKeyColumn = columns.find { it.isPrimaryKey == 1 }

        return primaryKeyColumn?.columnName
    }


    // 추가 테이블 컬럼 key, value 찾기
    private fun getColumnsMap(tableId: Long): Map<Int, Map<String, Any>>? {
        val map = mutableMapOf<Int, MutableMap<String, Any>>()
        val columns = sourceColumnService.getColumnsByTableId(tableId)

        // 각 컬럼의 데이터를 그룹화하여 map에 저장
        columns.forEach { column ->
            val sourceDataList = sourceDataService.getDataListByColumnId(column.columnId)
            sourceDataList.forEach { data ->
                val columnLine = data.columnLine
                val dataMap = map.getOrPut(columnLine) { mutableMapOf() }
                dataMap[column.columnName] = data.data
            }
        }

        return map
    }

    // 추가된 행과 데이터 찾기
    private fun getAddColumnAndData(newTables: List<SourceTable>, oldTables: List<SourceTable>): List<ChangeTable> {
        // 새로운 테이블과 이전 테이블 중에서 활성화된(삭제되지 않은) 테이블을 가져옴
        val newActiveTables = getActiveTables(newTables, 0)
        val oldActiveTables = getActiveTables(oldTables, 0)

        // 추가된 열과 데이터를 저장할 리스트 생성
        val addedColumnsAndData = mutableListOf<ChangeTable>()

        // 새로운 테이블과 이전 테이블 중에서 테이블 이름이 같은 것을 찾음
        val commonTables = mutableListOf<Pair<SourceTable, SourceTable>>()
        for (newTable in newActiveTables) {
            val oldTable = oldActiveTables.find { it.tableName == newTable.tableName }
            oldTable?.let { commonTables.add(Pair(newTable, oldTable)) }
        }

        // 같은 이름을 가진 테이블 끼리 비교 시작
        commonTables.forEach { (newTable, oldTable) ->
            // 열 목록 가져오기
            val newColumns = sourceColumnService.getColumnsByTableId(newTable.tableId)
            val oldColumns = sourceColumnService.getColumnsByTableId(oldTable.tableId)

            // 값 목록 가져오기
            val newSourceDataList = mutableListOf<SourceData>()
            newColumns.forEach { column ->
                val data = sourceDataService.getDataListByColumnId(column.columnId)
                newSourceDataList.addAll(data)
            }
            val oldSourceData = mutableListOf<SourceData>()
            oldColumns.forEach { column ->
                val data = sourceDataService.getDataListByColumnId(column.columnId)
                oldSourceData.addAll(data)
            }

            // 차집합 데이터 찾기
            val addedData = newSourceDataList.filterNot { it in oldSourceData }
            // 중복 없이 columnLine 찾기
            val uniqueColumnLines = addedData.map { it.columnLine }.distinct()

            // 각 columnLine 번호에 대한 데이터를 저장할 Map을 만듭니다.
            val columnLineMap: Map<Int, Map<String, Any>> = uniqueColumnLines.associateWith { columnLine ->
                val dataWithColumnLine = newSourceDataList.filter { it.columnLine == columnLine }
                dataWithColumnLine.associate { sourceData ->
                    val column = newColumns.find { it.columnId == sourceData.columnId }
                    requireNotNull(column) { "Column not found for source data with columnId: ${sourceData.columnId}" }
                    column.columnName to sourceData.data
                }
            }
            // 변경된 테이블 정보를 ChangeTable 객체로 추가
            addedColumnsAndData.add(
                ChangeTable(
                    changeTableId = newTable.tableId,
                    changeTableName = newTable.tableName,
                    action = ChangeAction.ADD,
                    primaryKey = null,
                    columns = columnLineMap
                )
            )
        }
        return addedColumnsAndData
    }



    // -  ChangeTable DELETE 찾기 : B가 새로운 커밋

    // > 삭제된 테이블 찾기
    // 테이블들을 찾아서 (isDelete = 1 인것만 찾기)
    // 테이블의 차집합 꺼내기
    // 차집합을 ChangeTable로 생성

    // > 삭제된 행과 데이터 찾기
    // 같은 이름을 가진 테이블을 찾아 놓고 비교 시작
    // SourceColumn.columnId를 찾아냄
    // 그걸로 SourceData.columnId가 일치하는 데이터들을 뽑아와서 차집합 찾기
    // 그걸 ChangeTable로 생성
}