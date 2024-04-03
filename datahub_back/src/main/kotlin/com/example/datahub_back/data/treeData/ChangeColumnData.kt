package com.example.datahub_back.data.treeData

import com.example.datahub_back.dto.treeDTO.ChangeColumn

val changeColumn1 = ChangeColumn(
    columnId = 1,
    table = changeTable1,
    columnName = "이름",
    comment = "This is a sample column 1",
    dataType = "VARCHAR",
    isPrimaryKey = 1,
    isForeignKey = 0,
    isUniqueKey = 0,
    joinSourceTableId = 1 // 예시로 null 처리
)
val changeColumn2 = ChangeColumn(
    columnId = 2,
    table = changeTable1,
    columnName = "전화번호",
    comment = "This is a sample column 1",
    dataType = "VARCHAR",
    isPrimaryKey = 1,
    isForeignKey = 0,
    isUniqueKey = 0,
    joinSourceTableId = null // 예시로 null 처리
)

val changeColumn3 = ChangeColumn(
    columnId = 3,
    table = changeTable1,
    columnName = "나이",
    comment = "This is a sample column 1",
    dataType = "VARCHAR",
    isPrimaryKey = 1,
    isForeignKey = 0,
    isUniqueKey = 0,
    joinSourceTableId = null // 예시로 null 처리
)

val changeColumn4 = ChangeColumn(
    columnId = 4,
    table = changeTable1,
    columnName = "생일",
    comment = "This is a sample column 1",
    dataType = "VARCHAR",
    isPrimaryKey = 1,
    isForeignKey = 0,
    isUniqueKey = 0,
    joinSourceTableId = null // 예시로 null 처리
)

val changeColumn5 = ChangeColumn(
    columnId = 5,
    table = changeTable2,
    columnName = "SampleColumn5",
    comment = "This is a sample column 2",
    dataType = "INTEGER",
    isPrimaryKey = 0,
    isForeignKey = 1,
    isUniqueKey = 0,
    joinSourceTableId = null // 예시로 null 처리
)

val changeColumnList = mutableListOf(
    changeColumn1,
    changeColumn2, changeColumn3,
    changeColumn4, changeColumn5
)