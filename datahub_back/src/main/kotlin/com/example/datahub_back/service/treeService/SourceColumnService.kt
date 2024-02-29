package com.example.datahub_back.service.treeService

import com.example.datahub_back.data.treeData.sourceColumnList
import com.example.datahub_back.dto.treeDTO.SourceColumn
import org.springframework.stereotype.Service

@Service
class SourceColumnService {

    fun getColumnByTableId(tableId: Long) = sourceColumnList.filter { it.tableId == tableId }

    fun getColumnByColumId(columId: Long): SourceColumn? = sourceColumnList.find { it.columnId == columId }

    fun createColumn(column: SourceColumn): SourceColumn {
        sourceColumnList.add(column)
        return column
    }

    fun updateColumn(id: Long, updatedColumn: SourceColumn): SourceColumn? {
        val index = sourceColumnList.indexOfFirst { it.columnId == id }
        return if (index != -1) {
            sourceColumnList[index] = updatedColumn
            updatedColumn
        } else {
            null
        }
    }

    fun deleteColumn(id: Long): SourceColumn? {
        val column = sourceColumnList.find { it.columnId == id }
        if (column != null) {
            sourceColumnList.remove(column)
        }
        return column
    }
}