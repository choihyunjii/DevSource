package com.example.datahub_back.service.treeService

import com.example.datahub_back.dto.treeDTO.*
import com.example.datahub_back.service.treeService.tableColumnData.*
import org.springframework.stereotype.Service

@Service
class SaveDataService(
    private val changeTableService: ChangeTableService,
    private val changeColumnService: ChangeColumnService,
    private val changeDataService: ChangeDataService,
    private val sourceTableService: SourceTableService,
    private val sourceColumnService: SourceColumnService,
    private val sourceDataService: SourceDataService,
) {
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