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
    val sourceTables: List<SourceTable>?,
    val changeTables: List<ChangeTable>?,
    val changePageIds: List<Long>?
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
    val changeTableId: Long, // PK, FK Table의 tableId와 일치해야 함
    val changeTableName: String,
    val action: ChangeAction, // 변경 액션 (추가, 삭제)
    val primaryKey: String?, // 변경된 행의 기본 키 이름
    val columns: MutableList<ChangeTableColumn> // 변경된 컬럼들과 그 값들
)

data class ChangeTableColumn(
    val changeTableId: Long, //PK, FK
    val columnIndex: Int,
    val columnName: String,
    val columnValue: String
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