package com.example.datahub_back.data.devTree

import com.example.datahub_back.dto.devTree.SourceData

val sourceData1 = SourceData(
    dataId = 1,
    columnId = sourceColumnList[0].columnId,
    data = "Sample data 1"
)

val sourceData2 = SourceData(
    dataId = 2,
    columnId = sourceColumnList[1].columnId,
    data = "100"
)

val sourceDataList = mutableListOf(sourceData1, sourceData2)

