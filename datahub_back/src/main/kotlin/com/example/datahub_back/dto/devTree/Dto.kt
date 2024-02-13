package com.example.datahub_back.dto.devTree

import java.time.LocalDateTime

data class User(
    val userId: String, // PK
    val password: String,
    val name: String,
    val phoneNumber: String,
    val email: String
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
    val name: String,
    val userId: User, // FK
    val projectId: Project, // FK
    val push: Int,
    val pull: Int,
    val crash: Int,
    val isMainBranch: Int
)

data class Commit(
    val commitId: String, // PK
    val comment: String,
    val createTime: LocalDateTime,
    val createUser: String, // FK
    val branchId: Branch // FK
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

data class ChangeData(
    val changeDataId: Int, // PK
    val columId: Colum, // FK
    val action: Int, // 삭제 0, 추가 1
    val data: String
)

data class Page(
    val pageId: Int, // PK
    val commitId: Commit, // PK, FK
    val path: String,
    val isFavorite: Int,
    val isDelete: Int,
    val updateTime: LocalDateTime
)