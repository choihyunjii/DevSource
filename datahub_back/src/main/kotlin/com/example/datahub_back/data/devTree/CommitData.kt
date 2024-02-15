package com.example.datahub_back.data.devTree

import com.example.datahub_back.dto.devTree.Commit
import java.time.LocalDateTime

val commit1 = Commit(
    commitId = 1, // 해시코드
    comment = "user1",
    createTime = LocalDateTime.now(),
    createUser = user1.userId,
    branchId = branch1
)

val commit2 = Commit(
    commitId = 2,
    comment = "Bug fix",
    createTime = LocalDateTime.now(),
    createUser = "user1",
    branchId = branch1
)

val commit3 = Commit(
    commitId = 3,
    comment = "Initial commit",
    createTime = LocalDateTime.now(),
    createUser = "user1",
    branchId = branch2
)

val commit4 = Commit(
    commitId = 4,
    comment = "Bug fix",
    createTime = LocalDateTime.now(),
    createUser = "user1",
    branchId = branch2
)

val commit5 = Commit(
    commitId = 5,
    comment = "Initial commit",
    createTime = LocalDateTime.now(),
    createUser = "user1",
    branchId = branch3
)

val commit6 = Commit(
    commitId = 6,
    comment = "Bug fix",
    createTime = LocalDateTime.now(),
    createUser = "user1",
    branchId = branch3
)

val commitList = listOf(commit1, commit2, commit3, commit4, commit5, commit6)

