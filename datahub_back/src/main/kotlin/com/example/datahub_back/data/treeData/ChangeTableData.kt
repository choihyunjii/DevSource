package com.example.datahub_back.data.treeData

import com.example.datahub_back.dto.treeDTO.ChangeAction
import com.example.datahub_back.dto.treeDTO.ChangeTable
import com.example.datahub_back.dto.treeDTO.ChangeTableColumn

val change1 = ChangeTable(
    changeTableId = 1,
    changeTableName = "Table1",
    action = ChangeAction.ADD,
    primaryKey = "tableId",
    columns = mutableListOf(
        ChangeTableColumn(
            changeTableId = 1,
            columnIndex = 1,
            columnName = "tableName",
            columnValue = "NewTable"
        ),
        ChangeTableColumn(
            changeTableId = 1,
            columnIndex = 2,
            columnName = "comment",
            columnValue = "This is a new table."
        ),
        ChangeTableColumn(
            changeTableId = 1,
            columnIndex = 3,
            columnName = "isFavorite",
            columnValue = "1"
        ),
        ChangeTableColumn(
            changeTableId = 1,
            columnIndex = 4,
            columnName = "isDelete",
            columnValue = "0"
        )
    )
)

val change2 = ChangeTable(
    changeTableId = 2,
    changeTableName = "Table2",
    action = ChangeAction.ADD,
    primaryKey = "tableId",
    columns = mutableListOf(
        ChangeTableColumn(
            changeTableId = 2,
            columnIndex = 1,
            columnName = "comment",
            columnValue = "Updated comment."
        ),
        ChangeTableColumn(
            changeTableId = 2,
            columnIndex = 2,
            columnName = "isFavorite",
            columnValue = "0"
        )
    )
)

val changeList = mutableListOf(change1, change2)
