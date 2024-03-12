package com.example.datahub_back.service.tableService

import com.example.datahub_back.controller.toolController.table.TableResponse
import com.example.datahub_back.controller.toolController.table.TableStatusResponse
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
    override fun getTable(tableID: Long) : TableResponse {
        val findTable = findByTableID(tableID)
        val columns = findColumnsByTableID(findTable)

        return TableResponse(
            id = findTable.id,
            table = createTable(columns)
        )
    }
    fun getTablesByDatabase(dataBase: DataBase) : List<Table> =
        exampleTableList.filter { it.dataBase == dataBase }

    fun createTable(columns: List<Column>) : MutableMap<String, List<Data>> {
        val tableMap: MutableMap<String, List<Data>> = mutableMapOf()

        columns.forEach { column ->
            val columnData = findDataByColumn(column)
            val sortedData = sortColumnLine(columnData) //데이터 정렬 후

            tableMap[column.name] = sortedData
        }
        return tableMap
    }

    private fun findByTableID(tableID: Long) : Table =
        exampleTableList.first() { it.id == tableID }

    private fun findColumnsByTableID(table: Table): List<Column> =
        exampleColumnList.filter {
            it.table == table
        }

    private fun findDataByColumn(column: Column) : List<Data> =
        exampleDataList.filter {
            it.column == column
        }

    private fun sortColumnLine(data: List<Data>): List<Data> =
        data.sortedBy { it.columLine }


    override fun findTableStatusByDatabaseID(dataBaseID: Long): TableStatusResponse =
        TableStatusResponse(
            id = dataBaseID,
            isDeleteTables = findDeleteTables(),
            isFavoriteTables = findFavoriteTables(),
            isAllTables = findAllTables()
        )

    private fun findDeleteTables() : List<Table> =
        exampleTableList.filter { it.isDelete == 1 }

    private fun findFavoriteTables() : List<Table> =
        exampleTableList.filter { it.isFavorite == 1 }

    private fun findAllTables() : List<Table> =
        exampleTableList.filter { it.isDelete != 1 }

}