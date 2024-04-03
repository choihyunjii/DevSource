package com.example.datahub_back.service.treeService.commit

import com.example.datahub_back.dto.toolDTO.*
import com.example.datahub_back.dto.treeDTO.*
import com.example.datahub_back.service.backDataService.*
import com.example.datahub_back.service.tableService.TableService
import com.example.datahub_back.service.treeService.commit.DataTransformer.Companion.toSourceColumn
import com.example.datahub_back.service.treeService.commit.DataTransformer.Companion.toSourceData
import com.example.datahub_back.service.treeService.commit.DataTransformer.Companion.toSourceTable
import org.springframework.stereotype.Service

@Service
class DataTransformationService (
    private val dataBaseService: DataBaseService,
    private val tableService: TableService,
    private val columnService: ColumnService,
    private val dataService: DataService
) {
    // Tool 데이터들을 모두 Tree 형으로 변환 시키기
    fun executeChangeOperations(project: Project, newCommit: Commit): Triple<List<SourceTable>, List<SourceColumn>, List<SourceData>>? {
        val dataBase = getDataBase(project) ?: return null

        val sourceTables = mutableListOf<SourceTable>()
        val sourceColumns = mutableListOf<SourceColumn>()
        val sourceData = mutableListOf<SourceData>()

        val toolTables = getToolTables(dataBase)
        for (table in toolTables) {
            val toolColumns = getToolColumns(table.id)
            val toolData = getToolData(toolColumns.map { it.id })

            sourceTables.add(table.toSourceTable(newCommit))
            sourceColumns.addAll(toolColumns.map { it.toSourceColumn(newCommit) })
            sourceData.addAll(toolData.map { it.toSourceData(newCommit) })
        }
        return Triple(sourceTables, sourceColumns, sourceData)
    }

    private fun getDataBase(project: Project) : DataBase? =
        project.id?.let { dataBaseService.getDataBaseById(it) }

    private fun getToolTables(dataBase: DataBase) : List<Table> =
        tableService.getTablesByDatabase(dataBase)

    private fun getToolColumns(tableId: Long) : List<Column> =
        columnService.getColumnNameByTableId(tableId)

    private fun getToolData(columnIds: List<Long>): List<Data> =
        dataService.getDataByColumns(columnIds)
}
