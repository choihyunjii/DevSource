package com.example.datahub_back.data.devTree
import com.example.datahub_back.dto.devTree.ChangeTable


val changeTable1 = ChangeTable(
    changeTableId = 1,
    columNumber = 1,
    rowNumber = 1,
    tableName = "Table1",
    action = '-',
    columName = "이름",
    data = "김보영",
)

val changeTable2 = ChangeTable(
    changeTableId = 2,
    columNumber = 2,
    rowNumber = 1,
    tableName = "Table1",
    action = '-',
    columName = "사는 곳",
    data = "부천",
)

val changeTableList = mutableListOf(changeTable1, changeTable2)
