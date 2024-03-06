package com.example.datahub_back.service.treeService.commit

import com.example.datahub_back.dto.toolDTO.*
import com.example.datahub_back.dto.treeDTO.*
import com.example.datahub_back.service.backDataService.*
import org.springframework.stereotype.Service

@Service
class DataTransformationService (
    private val dataBaseService: DataBaseService,
    private val tableService: TableService,
    private val columnService: ColumnService,
    private val dataService: DataService
) {
    // Tool 데이터들을 모두 Tree 형으로 변환 시키기
    fun executeChangeOperations(projectId: Long): Triple<List<SourceTable>, List<SourceColumn>, List<SourceData>>? {
        val dataBase = getDataBase(projectId) ?: return null

        val sourceTables = mutableListOf<SourceTable>()
        val sourceColumns = mutableListOf<SourceColumn>()
        val sourceData = mutableListOf<SourceData>()

        val toolTables = getToolTables(dataBase)
        for (table in toolTables) {
            val toolColumns = getToolColumns(table.id)
            val toolData = getToolData(toolColumns.map { it.id })

            sourceTables.add(changeTableToSourceTable(table))
            sourceColumns.addAll(changeColumnsToSourceColumns(toolColumns))
            sourceData.addAll(changeDataToSourceData(toolData, toolColumns))
        }
        return Triple(sourceTables, sourceColumns, sourceData)
    }

    private fun getDataBase(projectId: Long) : DataBase? =
        dataBaseService.getDataBaseById(projectId)

    private fun getToolTables(dataBase: DataBase) : List<Table> =
        tableService.getTablesByDatabase(dataBase)

    private fun getToolColumns(tableId: Long) : List<Column> =
        columnService.getColumnNameByTableId(tableId)

    private fun getToolData(columnIds: List<Long>): List<Data> =
        dataService.getDataByColumns(columnIds)

    private fun changeTableToSourceTable(table: Table): SourceTable =
        SourceTable(
            tableId = table.id,
            tableName = table.name,
            comment = table.comment,
            isFavorite = table.isFavorite,
            isDelete = table.isDelete,
            updateTime = table.updateTime
        )

    private fun changeColumnsToSourceColumns(columns: List<Column>): List<SourceColumn> =
        columns.map { column ->
            SourceColumn(
                tableId = column.table.id,
                columnId = column.id,
                columnName = column.name,
                dataType = column.dataType,
                isPrimaryKey = column.isPrimaryKey,
                isForeignKey = column.isForeignKey,
                isUniqueKey = column.isUniqueKey,
                joinSourceTable = column.joinTable?.let { changeTableToSourceTable(it) }
            )
        }

    private fun changeDataToSourceData(dataList: List<Data>, columns: List<Column>): List<SourceData> {
        val sourceDataList = mutableListOf<SourceData>()
        for (data in dataList) {
            val column = columns.find { it.id == data.column.id } ?: continue
            sourceDataList.add(
                SourceData(
                    dataId = data.id,
                    columnId = data.column.id,
                    columnLine = data.columLine,
                    data = data.data
                )
            )
        }
        return sourceDataList
    }
}
