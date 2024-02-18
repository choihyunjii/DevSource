package com.example.datahub_back.data.devTree

import com.example.datahub_back.dto.devTree.Colum

val table1Columns = mutableListOf<Colum>(
    Colum(
        columId = 1,
        tableId = 1,
        columName = "Column 1",
        dataType = "String",
        isPrimaryKey = 1,
        isForeignKey = 0,
        isUniqueKey = 0,
        joinTable = null
    ),
    Colum(
        columId = 2,
        tableId = 1,
        columName = "Column 2",
        dataType = "Int",
        isPrimaryKey = 0,
        isForeignKey = 0,
        isUniqueKey = 0,
        joinTable = null
    ),
    Colum(
        columId = 3,
        tableId = 1,
        columName = "Column 3",
        dataType = "Boolean",
        isPrimaryKey = 0,
        isForeignKey = 0,
        isUniqueKey = 1,
        joinTable = null
    )
)