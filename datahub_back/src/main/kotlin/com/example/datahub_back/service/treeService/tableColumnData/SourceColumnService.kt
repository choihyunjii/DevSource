package com.example.datahub_back.service.treeService.tableColumnData

import com.example.datahub_back.data.treeData.sourceColumnList
import com.example.datahub_back.dto.treeDTO.SourceColumn
import com.example.datahub_back.dto.treeDTO.SourceTable
import org.springframework.stereotype.Service

@Service
class SourceColumnService {

    fun getColumnsByTable(table: SourceTable) = sourceColumnList.filter { it.table == table }

    fun getColumnIsPrimaryKeyByTable(table: SourceTable) = sourceColumnList.find { it.table == table && it.isPrimaryKey == 1}

    private fun findPKColumn(columnList: List<SourceColumn>, table: SourceTable)  =
        columnList.find {it.table == table && it.isPrimaryKey == 1}

    fun getColumnByColumId(columId: Long) = sourceColumnList.find { it.columnId == columId }

    fun createColumn(column: SourceColumn): SourceColumn {
        sourceColumnList.add(column)
        return column
    }

    fun getColumnMaxId() = sourceColumnList.maxByOrNull { it.columnId }?.columnId ?: 1
}
