package com.example.datahub_back.data.treeData

import com.example.datahub_back.dto.treeDTO.ChangeData

val changeData1 = ChangeData(
    dataId = 1,
    column = changeColumn1,
    columnLine = 1,
    data = "Sample data 1",
    action = 1
)

val changeData2 = ChangeData(
    dataId = 2,
    column = changeColumn2,
    columnLine = 2,
    data = "Sample data 2",
    action = 1
)

val changeDataList = mutableListOf(changeData1, changeData2)