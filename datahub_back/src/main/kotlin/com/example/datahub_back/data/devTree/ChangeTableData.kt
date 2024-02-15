package com.example.datahub_back.data.devTree

import com.example.datahub_back.dto.devTree.ChangePage
import com.example.datahub_back.dto.devTree.ChangeTable
import com.example.datahub_back.dto.devTree.Commit


val changeTable1 = ChangeTable(
    commitId = commit1,
    tableName = "Table1",
    columNumber = 1,
    rowNumber = 1,
    action = '-',
    columName = "이름",
    data = "김보영",
)

val changeTable2 = ChangeTable(
    commitId = commit1,
    tableName = "Table1",
    columNumber = 2,
    rowNumber = 1,
    action = '-',
    columName = "나이",
    data = "25",
)

val changeTableList = listOf(changeTable1, changeTable2)
