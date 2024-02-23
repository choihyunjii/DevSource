package com.example.datahub_back.data.treeData
import com.example.datahub_back.dto.treeDTO.ChangeTable


val changeTable1 = ChangeTable(
    changeTableId = 1,
    columnNumber = 1,
    rowNumber = 1,
    tableName = "Table1",
    action = '-',
    columName = "이름",
    data = "김보영",
)

val changeTable2 = ChangeTable(
    changeTableId = 2,
    columnNumber = 2,
    rowNumber = 1,
    tableName = "Table1",
    action = '-',
    columName = "사는 곳",
    data = "부천",
)

val changeTableList = mutableListOf(changeTable1, changeTable2)
