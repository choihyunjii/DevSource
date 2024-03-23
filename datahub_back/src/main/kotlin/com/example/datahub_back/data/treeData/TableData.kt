package com.example.datahub_back.data.treeData

import com.example.datahub_back.dto.treeDTO.SourceTable
import java.time.LocalDateTime

val table1 = SourceTable(
    tableId = 1,
    tableName = "학생 테이블",
    comment = "학생들의 정보를 모아둔 테이블",
    isFavorite = 1,
    isDelete = 0,
    updateTime = LocalDateTime.now(),
    commit = commit2
)

val table2 = SourceTable(
    tableId = 1,
    tableName = "학생 테이블",
    comment = "학생들의 정보를 모아둔 테이블",
    isFavorite = 1,
    isDelete = 0,
    updateTime = LocalDateTime.now(),
    commit = commit1
)

val sourceTableList = mutableListOf(table1, table2)
