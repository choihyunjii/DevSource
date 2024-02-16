package com.example.datahub_back.dto.devTree

import java.time.LocalDateTime

data class User(
    val userId: String, // PK
    val password: String,
    val userName: String,
    val phoneNumber: String,
    val email: String,
)

data class Project(
    val projectId: Int, // PK
    val projectName: String,
    val comment: String,
    val createUser: String,
    val createTime: LocalDateTime,
    val isFavorite: Int,
    val isDelete: Int,
    val teamUsers: MutableList<User>
)

data class Branch(
    val branchId: Int, // PK
    val userId: String?, // FK
    val projectId: Int, // FK
    val push: Int,
    val pull: Int,
    val crash: Int,
    val isMainBranch: Int
)

data class Commit(
    val commitId: Int, // PK
    val branchId: Int, // PK, FK
    val comment: String,
    val createTime: LocalDateTime,
    val createUser: String,
    val tables: MutableList<Table>,
    val pages: MutableList<Page>,
    val changeTables: MutableList<ChangeTable>,
    val changePages: MutableList<ChangePage>
)

data class Table(
    val tableId: Int, // PK
    val tableName: String,
    val comment: String,
    val isFavorite: Int,
    val isDelete: Int,
    val updateTime: LocalDateTime
)

data class Colum(
    val tableId: Int, // PK, FK
    val columId: Int, // PK
    val columName: String,
    val dataType: String,
    val isPrimaryKey: Int,
    val isForeignKey: Int,
    val isUniqueKey: Int,
    val joinTable: Table?
)

data class Data(
    val dataId: Int, // PK
    val columId: Int, // FK
    val data: String
)

data class Page(
    val pageId: Int, // PK
    val pageName: String,
    val path: String,
    val isFavorite: Int,
    val isDelete: Int,
    val updateTime: LocalDateTime
)

// 테이블 변경사항
data class ChangeTable(
    val changeTableId: Int, // PK, Table의 tableId와 일치해야 함
    val columNumber: Int, // PK
    val rowNumber: Int, // PK
    val tableName: String,
    val action: Char, // -, +
    val columName: String,
    val data: String
)

// 페이지 변경사항
data class ChangePage(
    val pageId: Int, // PK, Page의 pageId와 일치해야 함
    val pageName: String,
    val path: String
)