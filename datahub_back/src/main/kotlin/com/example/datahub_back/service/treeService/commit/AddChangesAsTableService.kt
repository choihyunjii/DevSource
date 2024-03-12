package com.example.datahub_back.service.treeService.commit

import com.example.datahub_back.dto.treeDTO.SourceColumn
import com.example.datahub_back.dto.treeDTO.SourceData
import com.example.datahub_back.dto.treeDTO.SourceTable
import com.example.datahub_back.service.treeService.SourceColumnService
import com.example.datahub_back.service.treeService.SourceDataService
import com.example.datahub_back.service.treeService.SourceTableService
import org.springframework.stereotype.Service

//    [새로 생성된 테이블 찾기]
//    0. toolAllData와 oldTables의 테이블 중 삭제되지 않은 테이블 뽑아서 리스트 저장
//    1. 테이블 이름으로 차집합을 찾아서 비교
//    2. 새로 생성된 테이블 찾아서 ChangeTable 생성 및 저장 (테이블 자체가 새로 생성된거니 컬럼과 데이터 비교 X)
//    3. 그 리스트로 반복문 시작
//    4. 그 테이블 리스트로 찾은 ChangeColumn을 그대로 생성 및 저장
//    6. 생성한 리스트로 반복문 시작
//    7. 그 컬럼 리스트로 찾은 ChangeData을 그대로 생성 및 저장
@Service
class AddChangesAsTableService (
    private val sourceTableService: SourceTableService,
    private val sourceColumnService: SourceColumnService,
    private val sourceDataService: SourceDataService,
){
    fun addChangesAsTable(oldTables: List<SourceTable>?,
                          toolAllData: Triple<List<SourceTable>, List<SourceColumn>, List<SourceData>>?) {
        if (oldTables != null && toolAllData != null) {
            val (newTables, newColumns, newData) = toolAllData
            val newTablesNotDelete = sourceTableService.getTablesByIsDelete(newTables, 0)
            val oldTablesNotDelete = sourceTableService.getTablesByIsDelete(oldTables, 0)

            // 새로운 테이블 중에서 이전에 없던 테이블을 찾음
            val newAddTables = newTablesNotDelete.filterNot { it in oldTablesNotDelete }

            newAddTables.forEach { table ->
                sourceTableService.createTable(table)
                val newAddColumns = sourceColumnService.getColumnsByTableId(table)
                newAddColumns.forEach { column ->
                    sourceColumnService.createColumn(column)
                    val newAddData = sourceDataService.getDataListByColumn(column)
                    newAddData.forEach { data ->
                        sourceDataService.createData(data)
                    } // forEach
                } // forEach
            } // forEach
        } // if
    } // fun

//    private fun processDataItems(sourceTables: List<SourceTable>, columns: List<SourceColumn>, data: List<SourceData>) {
//        addTables(sourceTables)
//        addColumns(columns)
//        addDataItems(data)
//    }

    private fun addTables(sourceTables: List<SourceTable>) {
        sourceTables.forEach { sourceTableService.createTable(it) }
    }

    private fun addColumns(columns: List<SourceColumn>) {
        columns.forEach { sourceColumnService.createColumn(it) }
    }

    private fun addDataItems(dataItems: List<SourceData>) {
        dataItems.forEach { sourceDataService.createData(it) }
    }
}