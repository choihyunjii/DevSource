package com.example.datahub_back.data.treeData

import com.example.datahub_back.dto.treeDTO.ChangeAction
import com.example.datahub_back.dto.treeDTO.ChangeTable


val change1 = ChangeTable(
    changeTableId = 1,
    changeTableName = "Table1",
    action = ChangeAction.ADD,
    primaryKey = "tableId",
    columns = mapOf(
        1 to mapOf(
            "tableName" to "NewTable",
            "comment" to "This is a new table.",
            "isFavorite" to 1,
            "isDelete" to 0
        ),
        2 to mapOf(
            "tableName" to "NewTable2",
            "comment" to "This is a new table.2",
            "isFavorite" to 0,
            "isDelete" to 0
        )
    )
)

val change2 = ChangeTable(
    changeTableId = 2,
    changeTableName = "Table2",
    action = ChangeAction.ADD,
    primaryKey = "tableId",
    columns = mapOf(
        1 to mapOf(
            "comment" to "Updated comment.",
            "isFavorite" to 0
        )
    )
)

val changeList = mutableListOf(change1, change2)
