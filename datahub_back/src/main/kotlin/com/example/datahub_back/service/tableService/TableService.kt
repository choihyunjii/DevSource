package com.example.datahub_back.service.tableService

import com.example.datahub_back.controller.toolController.table.TableResponse
import com.example.datahub_back.data.toolData.exampleColumnList
import com.example.datahub_back.data.toolData.exampleDataList
import com.example.datahub_back.data.toolData.exampleTableList
import com.example.datahub_back.dto.toolDTO.Column
import com.example.datahub_back.dto.toolDTO.Data
import com.example.datahub_back.dto.toolDTO.DataBase
import com.example.datahub_back.dto.toolDTO.Table
import org.springframework.stereotype.Service

@Service
class TableService : TableRepository{

    override fun createTable(columns: List<Column>) : MutableMap<String, List<Data>> {
        val tableMap: MutableMap<String, List<Data>> = mutableMapOf()

        columns.forEach { column ->
            val columnData = findDataByColumn(column)
            val sortedData = sortColumnLine(columnData) //데이터 정렬 후

            tableMap[column.name] = sortedData
        }


        return tableMap
    }

    override fun findColumnsByTableID(table: Table): List<Column> =
        exampleColumnList.filter {
            it.table == table
        }



    private fun sortColumnLine(data: List<Data>): List<Data> =
        data.sortedBy { it.columLine }


    private fun findDataByColumn(column: Column) : List<Data> =
        exampleDataList.filter {
            it.column == column
        }

    private fun findByTableID(tableID: Long) : Table =
        exampleTableList.first() { it.id == tableID }

    fun getTable(tableID: Long) : TableResponse {
        val findTable = findByTableID(tableID)
        val columns = findColumnsByTableID(findTable)

        return TableResponse(
            findTable.id,
            createTable(columns)
        )
    }
    fun getTablesByDatabase(dataBase: DataBase) : List<Table> =
        exampleTableList.filter { it.dataBase == dataBase }
}