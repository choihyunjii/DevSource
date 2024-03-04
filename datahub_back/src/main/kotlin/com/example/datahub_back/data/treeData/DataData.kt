package com.example.datahub_back.data.treeData

import com.example.datahub_back.dto.treeDTO.SourceData

val sourceData1 = SourceData(
    dataId = 1,
    columnId = sourceColumnList[0].columnId,
    columnLine = 1,
    data = "Sample data 1"
)

val sourceData2 = SourceData(
    dataId = 2,
    columnId = sourceColumnList[1].columnId,
    columnLine = 2,
    data = "100"
)

val sourceDataList = mutableListOf(sourceData1, sourceData2)

