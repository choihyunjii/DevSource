package com.example.datahub_back.data.devTree

import com.example.datahub_back.dto.devTree.ChangeData

val changeData1 = ChangeData(
    changeDataId = 1,
    columId = table1Columns[0],
    action = 1,
    data = "New data"
)

val changeData2 = ChangeData(
    changeDataId = 2,
    columId = table1Columns[1],
    action = 0,
    data = "Deleted data"
)