package com.example.datahub_back.service.treeService.sourceTable

import com.example.datahub_back.data.treeData.changeColumnList
import com.example.datahub_back.data.treeData.sourceColumnList
import com.example.datahub_back.dto.treeDTO.ChangeColumn
import com.example.datahub_back.dto.treeDTO.ChangeTable
import com.example.datahub_back.dto.treeDTO.SourceColumn
import com.example.datahub_back.dto.treeDTO.SourceTable
import org.springframework.stereotype.Service

@Service
class ChangeColumnService {

    fun getColumnsByTableId(table: ChangeTable) = changeColumnList.filter { it.table == table }

    fun getColumnByColumId(columId: Long): ChangeColumn? = changeColumnList.find { it.columnId == columId }

    fun createColumn(column: ChangeColumn): ChangeColumn {
        changeColumnList.add(column)
        return column
    }

    fun updateColumn(id: Long, updatedColumn: ChangeColumn): ChangeColumn? {
        val index = changeColumnList.indexOfFirst { it.columnId == id }
        return if (index != -1) {
            changeColumnList[index] = updatedColumn
            updatedColumn
        } else {
            null
        }
    }

    fun deleteColumn(id: Long): ChangeColumn? {
        val column = changeColumnList.find { it.columnId == id }
        if (column != null) {
            changeColumnList.remove(column)
        }
        return column
    }
}
