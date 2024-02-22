package com.example.datahub_back.data.devTree

import com.example.datahub_back.dto.devTree.SourceColumn

val sourceColumn1 = SourceColumn(
    columnId = 1,
    tableId = 1,
    columnName = "Column 1",
    dataType = "String",
    isPrimaryKey = 1,
    isForeignKey = 0,
    isUniqueKey = 0,
    joinSourceTable = null
)

val sourceColumn2 = SourceColumn(
    columnId = 2,
    tableId = 1,
    columnName = "Column 2",
    dataType = "Int",
    isPrimaryKey = 0,
    isForeignKey = 0,
    isUniqueKey = 0,
    joinSourceTable = null
)

val sourceColumn3 = SourceColumn(
    columnId = 3,
    tableId = 1,
    columnName = "Column 3",
    dataType = "Boolean",
    isPrimaryKey = 0,
    isForeignKey = 0,
    isUniqueKey = 1,
    joinSourceTable = null
)


val sourceColumnList = mutableListOf(sourceColumn1, sourceColumn2, sourceColumn3)
