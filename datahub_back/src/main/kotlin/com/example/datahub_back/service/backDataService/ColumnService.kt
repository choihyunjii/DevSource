package com.example.datahub_back.service.backDataService

import com.example.datahub_back.data.toolData.exampleColumnList
import com.example.datahub_back.data.toolData.exampleDataList
import com.example.datahub_back.dto.templateDTO.ListDTO
import com.example.datahub_back.dto.toolDTO.Column
import com.example.datahub_back.dto.toolDTO.Table
import org.springframework.stereotype.Service

@Service
class ColumnService {

    fun getColumnNameByTableId(tableID: Long) : List<Column> = exampleColumnList.filter {
        it.table.id == tableID
    }.map { it }.toList()

    fun getColumnByTableAndColumnName(table:Table , columnName : String)
        = exampleColumnList.filter { it.table == table && columnName == it.name }[0]

    fun getColumnById(id: Long): Column? = exampleColumnList.find { it.id == id }

    fun getDataByColumnNameAndTableID(id: Long , columnName: String) =
        exampleDataList.filter { it.column.name == columnName && it.column.table.id == id }

    fun createColumn(column: Column): Column {
        exampleColumnList.add(column)
        return column
    }

    fun getColumnsAndData(tableID: Long) : MutableList<ListDTO> {
        val list : MutableList<ListDTO> = mutableListOf()
        val exampleColumnData = exampleColumnList.filter { it.table.id == tableID }
        exampleColumnData.forEach { column ->
            val dataList = exampleDataList.filter {
                it.column == column
            }.map { it.data }

            list.add(ListDTO(dataList))
        }
        return list
    }


    fun updateColumn(id: Long, updatedColumn: Column): Column? {
        val index = exampleColumnList.indexOfFirst { it.id == id }
        return if (index != -1) {
            exampleColumnList[index] = updatedColumn
            updatedColumn
        } else {
            null
        }
    }

    fun deleteColumn(id: Long): Column? {
        val column = exampleColumnList.find { it.id == id }
        if (column != null) {
            exampleColumnList.remove(column)
        }
        return column
    }
}
