package com.example.datahub_back.data.devTree

import com.example.datahub_back.dto.devTree.Commit
import java.time.LocalDateTime

val commit1 = Commit(
    commitId = "commit1",
    comment = "Initial commit",
    createTime = LocalDateTime.now(),
    createUser = user1.userId,
    branchId = branch1
)

val commit2 = Commit(
    commitId = "commit2",
    comment = "Bug fix",
    createTime = LocalDateTime.now(),
    createUser = user2.userId,
    branchId = branch2
)

