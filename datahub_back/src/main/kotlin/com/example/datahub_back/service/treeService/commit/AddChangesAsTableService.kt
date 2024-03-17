package com.example.datahub_back.service.treeService.commit

import com.example.datahub_back.dto.treeDTO.Commit
import com.example.datahub_back.dto.treeDTO.SourceTable
import com.example.datahub_back.service.treeService.commit.DataTransformer.Companion.toChangeColumn
import com.example.datahub_back.service.treeService.commit.DataTransformer.Companion.toChangeData
import com.example.datahub_back.service.treeService.commit.DataTransformer.Companion.toChangeTable
import com.example.datahub_back.service.treeService.tableColumnData.*
import org.springframework.stereotype.Service

@Service
class AddChangesAsTableService (
    private val sourceTableService: SourceTableService,
    private val sourceColumnService: SourceColumnService,
    private val sourceDataService: SourceDataService,
    private val changeTableService: ChangeTableService,
    private val changeColumnService: ChangeColumnService,
    private val changeDataService: ChangeDataService,
){
    fun addChangesAsTable(oldTables: List<SourceTable>?,
                          newTables: List<SourceTable>?,
                          newCommit: Commit
    ) {
        if (oldTables != null && newTables != null) {
            val newTablesNotDelete = sourceTableService.getTablesByIsDelete(newTables, 0)
            val oldTablesNotDelete = sourceTableService.getTablesByIsDelete(oldTables, 0)

            // 이름으로 비교해서 새로 생성된 테이블들 찾기
            val oldTablesNameSet = oldTablesNotDelete.map { it.tableName }.toSet()
            val newAddTables = newTablesNotDelete.filter {
                it.tableName !in oldTablesNameSet
            }
            println("새로 생성된 테이블 : $newAddTables")

            // 새로 생성된 테이블 데이터들 Change형으로 바꿔서 생성
            newAddTables.forEach { table ->
                changeTableService.createTable(table.toChangeTable(newCommit))
                val newAddColumns = sourceColumnService.getColumnsByTable(table)
                println(newAddColumns)
                newAddColumns.forEach { column ->
                    changeColumnService.createColumn(column.toChangeColumn(newCommit))
                    val newAddData = sourceDataService.getDataListByColumn(column)
                    println(newAddData)
                    newAddData.forEach { data ->
                        changeDataService.createData(data.toChangeData(newCommit, 1))
                    } // forEach
                } // forEach
            } // forEach
        } // if
    } // fun
}

//    [새로 생성된 테이블 찾기]
//    0. newTables와 oldTables의 테이블 중 삭제되지 않은 테이블 뽑아서 리스트 저장
//    1. 테이블 이름으로 차집합을 찾아서 비교
//    2. 새로 생성된 테이블 찾아서 ChangeTable 생성 및 저장 (테이블 자체가 새로 생성된거니 컬럼과 데이터 비교 X)
//    3. 그 리스트로 반복문 시작
//    4. 그 테이블 리스트로 찾은 ChangeColumn을 그대로 생성 및 저장
//    6. 생성한 리스트로 반복문 시작
//    7. 그 컬럼 리스트로 찾은 ChangeData을 그대로 생성 및 저장