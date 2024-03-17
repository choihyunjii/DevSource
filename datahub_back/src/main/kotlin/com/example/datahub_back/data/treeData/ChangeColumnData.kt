package com.example.datahub_back.data.treeData

import com.example.datahub_back.dto.treeDTO.ChangeColumn

val changeColumn1 = ChangeColumn(
    columnId = 1,
    table = changeTable1,
    columnName = "SampleColumn1",
    comment = "This is a sample column 1",
    dataType = "VARCHAR",
    isPrimaryKey = 1,
    isForeignKey = 0,
    isUniqueKey = 0,
    joinSourceTable = null // 예시로 null 처리
)

val changeColumn2 = ChangeColumn(
    columnId = 2,
    table = changeTable2,
    columnName = "SampleColumn2",
    comment = "This is a sample column 2",
    dataType = "INTEGER",
    isPrimaryKey = 0,
    isForeignKey = 1,
    isUniqueKey = 0,
    joinSourceTable = null // 예시로 null 처리
)

val changeColumnList = mutableListOf(changeColumn1, changeColumn2)