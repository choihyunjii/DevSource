package com.example.datahub_back.data.treeData

import com.example.datahub_back.dto.treeDTO.Commit
import java.time.LocalDateTime

val commit1 = Commit(
    commitId = 1, // 해시코드
    comment = "첫 커밋",
    createTime = LocalDateTime.now(),
    createUser = "user1",
    branchId = 1,
    sourceTableIds = mutableListOf(1),
    changeTableIds = mutableListOf(1, 2),
    changePageIds = mutableListOf(1)
)

val commit2 = Commit(
    commitId = 2,
    comment = "Bug fix",
    createTime = LocalDateTime.now(),
    createUser = "user1",
    branchId = 1,
    sourceTableIds = mutableListOf(1),
    changeTableIds = mutableListOf(),
    changePageIds = mutableListOf()
)

val commit3 = Commit(
    commitId = 3,
    comment = "Initial commit",
    createTime = LocalDateTime.now(),
    createUser = "user1",
    branchId = 2,
    sourceTableIds = mutableListOf(1),
    changeTableIds = mutableListOf(),
    changePageIds = mutableListOf()
)

val commit4 = Commit(
    commitId = 4,
    comment = "Bug fix",
    createTime = LocalDateTime.now(),
    createUser = "user1",
    branchId = 2,
    sourceTableIds = mutableListOf(1),
    changeTableIds = mutableListOf(),
    changePageIds = mutableListOf()
)

val commit5 = Commit(
    commitId = 5,
    comment = "Initial commit",
    createTime = LocalDateTime.now(),
    createUser = "user1",
    branchId = 3,
    sourceTableIds = mutableListOf(1),
    changeTableIds = mutableListOf(),
    changePageIds = mutableListOf()
)

val commit6 = Commit(
    commitId = 6,
    comment = "Bug fix",
    createTime = LocalDateTime.now(),
    createUser = "user1",
    branchId = 3,
    sourceTableIds = mutableListOf(1),
    changeTableIds = mutableListOf(),
    changePageIds = mutableListOf()
)

val commitList = mutableListOf(commit1, commit2, commit3, commit4, commit5, commit6)

