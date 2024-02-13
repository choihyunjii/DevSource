package com.example.datahub_back.data.devTree

import com.example.datahub_back.dto.devTree.Table
import com.example.datahub_back.dto.devTree.User
import java.time.LocalDateTime

val table1 = Table(
    tableId = 1,
    commitId = commit1,
    name = "Table 1",
    comment = "This is table 1",
    isFavorite = 1,
    isDelete = 0,
    updateTime = LocalDateTime.now()
)