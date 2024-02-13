package com.example.datahub_back.data.devTree

import com.example.datahub_back.dto.devTree.Colum

val table1Columns = mutableListOf<Colum>(
    Colum(
        columId = 1,
        tableId = table1,
        name = "Column 1",
        dataType = "String",
        isPrimaryKey = 1,
        isForeignKey = 0,
        isUniqueKey = 0,
        joinColum = null
    ),
    Colum(
        columId = 2,
        tableId = table1,
        name = "Column 2",
        dataType = "Int",
        isPrimaryKey = 0,
        isForeignKey = 0,
        isUniqueKey = 0,
        joinColum = null
    ),
    Colum(
        columId = 3,
        tableId = table1,
        name = "Column 3",
        dataType = "Boolean",
        isPrimaryKey = 0,
        isForeignKey = 0,
        isUniqueKey = 0,
        joinColum = null
    )
)