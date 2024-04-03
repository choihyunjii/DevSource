package com.example.datahub_back.service.treeService.tableColumnData

import com.example.datahub_back.data.treeData.changeColumnList
import com.example.datahub_back.data.treeData.changeTableList
import com.example.datahub_back.dto.treeDTO.ChangeColumn
import com.example.datahub_back.dto.treeDTO.ChangeTable
import org.springframework.stereotype.Service

@Service
class ChangeColumnService {

    fun getColumnsByTable(table: ChangeTable) = changeColumnList.filter { it.table == table }

    fun getColumnByColumId(columId: Long): ChangeColumn? = changeColumnList.find { it.columnId == columId }


    fun createColumn(column: ChangeColumn): ChangeColumn {
        changeColumnList.add(column)
        return column
    }

    fun getPrimaryKeyInColumns(columns: List<ChangeColumn>) = columns.find { it.isPrimaryKey == 1 }

    fun getColumnMaxId() = changeColumnList.maxByOrNull { it.columnId }?.columnId ?: 1

}
