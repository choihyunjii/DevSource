package com.example.datahub_back.data.treeData

import com.example.datahub_back.dto.treeDTO.ChangeTable
import java.time.LocalDateTime

val changeTable1 = ChangeTable(
    tableId = 1,
    tableName = "SampleTable1",
    comment = "This is a sample table 1",
    isFavorite = 1,
    isDelete = 0,
    updateTime = LocalDateTime.now(),
    commit = commit1,
)

val changeTable2 = ChangeTable(
    tableId = 2,
    tableName = "SampleTable2",
    comment = "This is a sample table 2",
    isFavorite = 0,
    isDelete = 1,
    updateTime = LocalDateTime.now(),
    commit = commit1,
)

val changeTableList = mutableListOf(changeTable1, changeTable2)

