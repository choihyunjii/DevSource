package com.example.datahub_back.data.treeData

import com.example.datahub_back.dto.treeDTO.*
import java.time.LocalDateTime

// 날짜 및 시간 임의로 지정
val createTime1 = LocalDateTime.of(2023, 5, 10, 9, 30)
val createTime2 = LocalDateTime.of(2023, 6, 15, 14, 45)
val createTime3 = LocalDateTime.of(2023, 7, 20, 11, 0)
val createTime4 = LocalDateTime.of(2023, 8, 25, 16, 15)
val createTime5 = LocalDateTime.of(2023, 9, 30, 13, 20)
val createTime6 = LocalDateTime.of(2023, 10, 5, 10, 45)

// 커밋 객체 생성
val commit1 = Commit(
    commitId = 1,
    comment = "첫 커밋",
    createTime = createTime1,
    createUser = "user1",
    branch = branch1,
    sourceTables = mutableListOf(table1),
    changeTables = mutableListOf(
        ChangeTable(
            changeTableId = 1,
            changeTableName = "Table1",
            action = ChangeAction.ADD,
            primaryKey = "tableId",
            columns = mutableListOf(
                ChangeTableColumn(1, 1, "tableName", "NewTable"),
                ChangeTableColumn(1, 2, "comment", "This is a new table."),
                ChangeTableColumn(1, 3, "isFavorite", "1"),
                ChangeTableColumn(1, 4, "isDelete", "0")
            )
        ),
        ChangeTable(
            changeTableId = 2,
            changeTableName = "Table2",
            action = ChangeAction.ADD,
            primaryKey = "tableId",
            columns = mutableListOf(
                ChangeTableColumn(2, 1, "comment", "Updated comment."),
                ChangeTableColumn(2, 2, "isFavorite", "0")
            )
        )
    ),
    changePageIds = mutableListOf(1)
)

val commit2 = Commit(
    commitId = 2,
    comment = "Bug fix",
    createTime = createTime2,
    createUser = "user1",
    branch = branch1,
    sourceTables = mutableListOf(table1),
    changeTables = mutableListOf(),
    changePageIds = mutableListOf()
)

val commit3 = Commit(
    commitId = 3,
    comment = "Initial commit",
    createTime = createTime3,
    createUser = "user1",
    branch = branch2,
    sourceTables = mutableListOf(table1),
    changeTables = mutableListOf(),
    changePageIds = mutableListOf()
)

val commit4 = Commit(
    commitId = 4,
    comment = "Bug fix",
    createTime = createTime4,
    createUser = "user1",
    branch = branch2,
    sourceTables = mutableListOf(table1),
    changeTables = mutableListOf(),
    changePageIds = mutableListOf()
)

val commit5 = Commit(
    commitId = 5,
    comment = "Initial commit",
    createTime = createTime5,
    createUser = "user1",
    branch = branch3,
    sourceTables = mutableListOf(table1),
    changeTables = mutableListOf(),
    changePageIds = mutableListOf()
)

val commit6 = Commit(
    commitId = 6,
    comment = "Bug fix",
    createTime = createTime6,
    createUser = "user1",
    branch = branch3,
    sourceTables = mutableListOf(table1),
    changeTables = mutableListOf(),
    changePageIds = mutableListOf()
)

// 커밋 리스트 생성
val commitList = mutableListOf(commit1, commit2, commit3, commit4, commit5, commit6)
