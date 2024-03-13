package com.example.datahub_back.data.treeData

import com.example.datahub_back.dto.treeDTO.SourceColumn

val sourceColumn1 = SourceColumn(
    columnId = 1,
    table = table1,
    columnName = "Column 1",
    comment = "comment1",
    dataType = "String",
    isPrimaryKey = 1,
    isForeignKey = 0,
    isUniqueKey = 0,
    joinSourceTableId = 1
)

val sourceColumn2 = SourceColumn(
    columnId = 2,
    table = table1,
    columnName = "Column 2",
    comment = "comment1",
    dataType = "Int",
    isPrimaryKey = 0,
    isForeignKey = 0,
    isUniqueKey = 0,
    joinSourceTableId = null
)

val sourceColumn3 = SourceColumn(
    columnId = 3,
    table = table1,
    columnName = "Column 3",
    comment = "comment1",
    dataType = "Boolean",
    isPrimaryKey = 0,
    isForeignKey = 0,
    isUniqueKey = 1,
    joinSourceTableId = null
)


val sourceColumnList = mutableListOf(sourceColumn1, sourceColumn2, sourceColumn3)
