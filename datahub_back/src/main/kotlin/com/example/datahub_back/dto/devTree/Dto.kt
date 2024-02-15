package com.example.datahub_back.dto.devTree

import java.time.LocalDateTime

data class User(
    val userId: String, // PK
    val password: String,
    val name: String,
    val phoneNumber: String,
    val email: String,
)

data class Project(
    val projectId: Int, // PK
    val name: String,
    val comment: String,
    val createUser: String,
    val createTime: LocalDateTime,
    val isFavorite: Int,
    val isDelete: Int,
    val teamUsers: MutableList<User>
)

data class Branch(
    val branchId: Int, // PK
    val userId: User?, // FK
    val projectId: Project, // FK
    val push: Int,
    val pull: Int,
    val crash: Int,
    val isMainBranch: Int
)

data class Commit(
    val commitId: Int, // PK
    val branchId: Branch, // PK, FK
    val comment: String,
    val createTime: LocalDateTime,
    val createUser: String,
)

data class Table(
    val tableId: Int, // PK
    val commitId: Commit, // PK, FK
    val name: String,
    val comment: String,
    val isFavorite: Int,
    val isDelete: Int,
    val updateTime: LocalDateTime
)

data class Colum(
    val columId: Int, // PK
    val tableId: Table, // PK, FK
    val name: String,
    val dataType: String,
    val isPrimaryKey: Int,
    val isForeignKey: Int,
    val isUniqueKey: Int,
    val joinColum: Colum?
)

data class Data(
    val dataId: Int, // PK
    val columId: Colum, // FK
    val data: String
)

data class Page(
    val pageId: Int, // PK
    val commitId: Commit, // PK, FK
    val pageName: String,
    val path: String,
    val isFavorite: Int,
    val isDelete: Int,
    val updateTime: LocalDateTime
)

// 테이블 변경사항
data class ChangeTable(
    val commitId: Commit, // PK, FK
    val tableName: String, // PK
    val columNumber: Int, // PK
    val rowNumber: Int, // PK
    val action: Char, // -, +
    val columName: String,
    val data: String
)

// 페이지 변경사항
data class ChangePage(
    val pageId: Int, // PK
    val commitId: Commit, // PK, FK
    val pageName: String,
    val path: String
)