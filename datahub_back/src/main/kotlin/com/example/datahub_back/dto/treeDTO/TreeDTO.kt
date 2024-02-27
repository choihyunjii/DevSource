package com.example.datahub_back.dto.treeDTO

import java.time.LocalDateTime

data class SourceUser(
    val userId: String, // PK
    val password: String,
    val userName: String,
    val phoneNumber: String,
    val email: String,
)

data class SourceProject(
    val projectId: Long, // PK
    val projectName: String,
    val comment: String,
    val createUser: String,
    val createTime: LocalDateTime,
    val isFavorite: Int,
    val isDelete: Int,
    val teamSourceUsers: MutableList<SourceUser>
)

data class Branch(
    val branchId: Long, // PK
    val userId: String?, // FK
    val projectId: Long, // FK
    var push: Int,
    val pull: Int,
    val crash: Int,
    val isMainBranch: Int
)

data class Commit(
    val commitId: Long, // PK (해시코드로 인해 중복)
    var branchId: Long, // PK, FK
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

data class SourceColumn(
    val tableId: Long, // PK, FK
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
    val data: String
)

// 테이블 변경사항
data class ChangeTable(
    val changeTableId: Long, // PK, Table의 tableId와 일치해야 함
    val columnNumber: Long, // PK
    val rowNumber: Long, // PK
    val tableName: String,
    val action: Char, // -, +
    val columName: String,
    val data: String
)

// 페이지 변경사항
data class ChangePage(
    val pageId: Long, // PK, Page의 pageId와 일치해야 함
    val pageName: String,
    val path: String
)