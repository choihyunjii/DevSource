package com.example.datahub_back.data.devTree
import com.example.datahub_back.dto.devTree.SourceTable
import java.time.LocalDateTime

val sourceTable1 = SourceTable(
    tableId = 1,
    tableName = "Table 1",
    comment = "This is table 1",
    isFavorite = 1,
    isDelete = 0,
    updateTime = LocalDateTime.now()
)

val sourceTableList = mutableListOf(sourceTable1)
