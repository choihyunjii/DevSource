package com.example.datahub_back.data.treeData

import com.example.datahub_back.dto.treeDTO.ChangeData

val changeData1 = ChangeData(
    dataId = 1,
    column = changeColumn1,
    columnLine = 1,
    data = "김 ㅇㅇ",
    action = 1
)


val changeData1_2 = ChangeData(
    dataId = 2,
    column = changeColumn2,
    columnLine = 1,
    data = "010-1111-2222",
    action = 1
)

val changeData1_3 = ChangeData(
    dataId = 3,
    column = changeColumn3,
    columnLine = 1,
    data = "13",
    action = 1
)


val changeData1_4 = ChangeData(
    dataId = 4,
    column = changeColumn4,
    columnLine = 1,
    data = "000000",
    action = 1
)

val changeData2 = ChangeData(
    dataId = 5,
    column = changeColumn1,
    columnLine = 2,
    data = "박 ㅇㅇ",
    action = 0
)


val changeData2_2 = ChangeData(
    dataId = 6,
    column = changeColumn2,
    columnLine = 2,
    data = "010-1111-2222",
    action = 0
)

val changeData2_3 = ChangeData(
    dataId = 7,
    column = changeColumn3,
    columnLine = 2,
    data = "14",
    action = 0
)


val changeData2_4 = ChangeData(
    dataId = 8,
    column = changeColumn4,
    columnLine = 2,
    data = "11111111",
    action = 0
)


val changeDataList =
    mutableListOf(changeData1, changeData1_2, changeData1_3, changeData1_4,
                    changeData2 , changeData2_2, changeData2_3, changeData2_4)