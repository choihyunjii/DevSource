package com.example.datahub_back.data.devTree

import com.example.datahub_back.dto.devTree.Commit
import java.time.LocalDateTime

val commit1 = Commit(
    commitId = 1, // 해시코드
    comment = "첫 커밋",
    createTime = LocalDateTime.now(),
    createUser = "user1",
    branchId = 1,
    sourceTables = mutableListOf(sourceTable1),
    changeTables = mutableListOf(changeTable1, changeTable2),
    changePages = mutableListOf(changePage1)
)

val commit2 = Commit(
    commitId = 2,
    comment = "Bug fix",
    createTime = LocalDateTime.now(),
    createUser = "user1",
    branchId = 1,
    sourceTables = mutableListOf(sourceTable1),
    changeTables = mutableListOf(),
    changePages = mutableListOf()
)

val commit3 = Commit(
    commitId = 3,
    comment = "Initial commit",
    createTime = LocalDateTime.now(),
    createUser = "user1",
    branchId = 2,
    sourceTables = mutableListOf(sourceTable1),
    changeTables = mutableListOf(),
    changePages = mutableListOf()
)

val commit4 = Commit(
    commitId = 4,
    comment = "Bug fix",
    createTime = LocalDateTime.now(),
    createUser = "user1",
    branchId = 2,
    sourceTables = mutableListOf(sourceTable1),
    changeTables = mutableListOf(),
    changePages = mutableListOf()
)

val commit5 = Commit(
    commitId = 5,
    comment = "Initial commit",
    createTime = LocalDateTime.now(),
    createUser = "user1",
    branchId = 3,
    sourceTables = mutableListOf(sourceTable1),
    changeTables = mutableListOf(),
    changePages = mutableListOf()
)

val commit6 = Commit(
    commitId = 6,
    comment = "Bug fix",
    createTime = LocalDateTime.now(),
    createUser = "user1",
    branchId = 3,
    sourceTables = mutableListOf(sourceTable1),
    changeTables = mutableListOf(),
    changePages = mutableListOf()
)

val commitList = mutableListOf(commit1, commit2, commit3, commit4, commit5, commit6)

