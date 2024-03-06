package com.example.datahub_back.dto.treeDTO

import java.time.LocalDateTime


data class Branch(
    val branchId: Long, // PK
    val userId: String?, // FK
    val projectId: Long, // FK
    var push: Int,
    var pull: Int,
    var crash: Int,
    val isMainBranch: Int
)

data class Commit(
    val commitId: Long, // PK
    var branchId: Long, // FK
    val comment: String,
    val createTime: LocalDateTime,
    val createUser: String,
    val sourceTableIds: MutableList<Long>,
    val changeTableIds: MutableList<Long>,
    val changePageIds: MutableList<Long>
)

data class SourceTable(
    var tableId: Long, // PK
    val tableName: String,
    val comment: String,
    val isFavorite: Int,
    val isDelete: Int,
    val updateTime: LocalDateTime
)

// 충돌 확인 로직
// 1. 테이블 끼리 이름으로 비교
// for (mainTable in mainTableList) main 테이블 (가장 최신 커밋)
//    for (sourceTable in sourceTableList) source 테이블 (가장 최신 커밋)
//       if (mainTable.tableName == sourceTable.tableName) 테이블 이름이 같다면
//           main과 source 각각 isPrimaryKey = 1인 컬럼을 찾고
//           if(두 개의 컬럼 이름이 같다면)
//                 행 충돌 리스트 add


data class SourceColumn(
    val tableId: Long, // FK
    val columnId: Long, // PK
    val columnName: String,
    val dataType: String,
    val isPrimaryKey: Int,
    val isForeignKey: Int,
    val isUniqueKey: Int,
    val joinSourceTable: SourceTable?
)

data class SourceData(
    val dataId: Long, // PK
    val columnId: Long, // FK
    val columnLine : Int,
    val data: String
)

// 테이블 변경사항
data class ChangeTable(
    val changeTableId: Long, // PK, Table의 tableId와 일치해야 함
    val changeTableName: String,
    val action: ChangeAction, // 변경 액션 (추가, 삭제)
    val primaryKey: String?, // 변경된 행의 기본 키 이름
    val columns: Map<Int, Map<String, Any>>? // 변경된 컬럼들과 그 값들
)

enum class ChangeAction {
    ADD, // 추가
    DELETE // 삭제
}

// 페이지 변경사항
data class ChangePage(
    val pageId: Long, // PK, Page의 pageId와 일치해야 함
    val pageName: String,
    val path: String
)