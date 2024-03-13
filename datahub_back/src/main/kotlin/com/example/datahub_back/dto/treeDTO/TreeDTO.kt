package com.example.datahub_back.dto.treeDTO

import com.example.datahub_back.dto.toolDTO.Profile
import com.example.datahub_back.dto.toolDTO.Project
import java.time.LocalDateTime


data class Branch(
    val branchId: Long, // PK
    val profile: Profile?, // FK
    val project: Project, // FK
    var push: Int, // 기본 0
    var pull: Int,
    var crash: Int,
    val isMainBranch: Int
)

data class Commit(
    val commitId: Int, // PK
    var branch: Branch, // PK, FK
    val comment: String,
    val createTime: LocalDateTime,
    val createUser: String,
)

data class SourceTable(
    var tableId: Long, // PK
    val tableName: String,
    val comment: String,
    val isFavorite: Int,
    val isDelete: Int,
    val updateTime: LocalDateTime,
    val commit: Commit,
)

data class SourceColumn(
    val columnId: Long, // PK
    val table: SourceTable, // PK, FK
    val columnName: String,
    val comment : String,
    val dataType: String,
    val isPrimaryKey: Int,
    val isForeignKey: Int,
    val isUniqueKey: Int,
    val joinSourceTableId: Long?
)

data class SourceData(
    val dataId: Long, // PK
    val column: SourceColumn, // PK, FK
    val columnLine : Int,
    val data: String
)

// 테이블 변경사항
data class ChangeTable(
    var tableId: Long, // PK
    val tableName: String,
    val comment: String,
    val isFavorite: Int,
    val isDelete: Int,
    val updateTime: LocalDateTime,
    val commit: Commit,
)

data class ChangeColumn(
    val columnId: Long, // PK
    val table: ChangeTable, // PK, FK
    val columnName: String,
    val comment : String,
    val dataType: String,
    val isPrimaryKey: Int,
    val isForeignKey: Int,
    val isUniqueKey: Int,
    val joinSourceTable: SourceTable?
)

data class ChangeData(
    val dataId: Long, // PK
    val column: ChangeColumn, // PK, FK
    val columnLine : Int,
    val data: String,
    val action: Int // 1: 추가, 0: 삭제
)

// 페이지 변경사항
data class ChangePage(
    val pageId: Long, // PK, Page의 pageId와 일치해야 함
    val pageName: String,
    val path: String,
    val commit: Commit,
)