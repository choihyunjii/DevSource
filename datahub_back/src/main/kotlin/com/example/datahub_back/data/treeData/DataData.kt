package com.example.datahub_back.data.treeData

import com.example.datahub_back.dto.treeDTO.SourceData

//val sourceData1 = SourceData(
//    dataId = 1,
//    column = sourceColumn1,
//    columnLine = 1,
//    data = "Sample data 1"
//)
//
//val sourceData2 = SourceData(
//    dataId = 2,
//    column = sourceColumn2,
//    columnLine = 2,
//    data = "100"
//)
//
//val sourceDataList = mutableListOf(sourceData1, sourceData2)
//

val sourceDataList = mutableListOf(
    SourceData(1, sourceColumn1, 1, "홍길동"),
    SourceData(5, sourceColumn1, 2, "김철수"),
    SourceData(9, sourceColumn1, 3, "이영희"),
    SourceData(13, sourceColumn1, 4, "박민수"),
    SourceData(2, sourceColumn2, 1, "20"),
    SourceData(6, sourceColumn2, 2, "22"),
    SourceData(10, sourceColumn2, 3, "21"),
    SourceData(14, sourceColumn2, 4, "23"),
    SourceData(3, sourceColumn3, 1, "010-1234-5678"),
    SourceData(7, sourceColumn3, 2, "010-9876-5432"),
    SourceData(11, sourceColumn3, 4, "010-5555-4444"),
    SourceData(15, sourceColumn3, 3, "010-7777-8888"),
    SourceData(4, sourceColumn4, 1, "서울시 강남구"),
    SourceData(8, sourceColumn4, 2, "서울시 서초구"),
    SourceData(12, sourceColumn4, 3, "경기도 고양시"),
    SourceData(16, sourceColumn4, 4, "인천시 남구"),
)
