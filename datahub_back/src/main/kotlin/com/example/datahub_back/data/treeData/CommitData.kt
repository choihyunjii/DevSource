package com.example.datahub_back.data.treeData

import com.example.datahub_back.dto.treeDTO.*
import java.time.LocalDateTime
val commit1 = Commit(
    commitId = 1,
    comment = "첫 번째 커밋",
    createTime = LocalDateTime.of(2023, 5, 10, 9, 30),
    createUser = "user1",
    branch = branch1,
    checkout = true
)

val commit2 = Commit(
    commitId = 2,
    comment = "버그 수정",
    createTime = LocalDateTime.of(2023, 5, 15, 13, 45),
    createUser = "user1",
    branch = branch1,
    checkout = false
)

val commit3 = Commit(
    commitId = 3,
    comment = "첫 번째 커밋",
    createTime = LocalDateTime.of(2023, 6, 1, 10, 15),
    createUser = "user1",
    branch = branch2,
    checkout = false
)

val commit4 = Commit(
    commitId = 4,
    comment = "버그 수정",
    createTime = LocalDateTime.of(2023, 6, 5, 14, 20),
    createUser = "user1",
    branch = branch2,
    checkout = true
)

val commit5 = Commit(
    commitId = 5,
    comment = "첫 번째 커밋",
    createTime = LocalDateTime.of(2023, 6, 20, 11, 0),
    createUser = "user1",
    branch = branch3,
    checkout = false
)

val commit6 = Commit(
    commitId = 6,
    comment = "버그 수정",
    createTime = LocalDateTime.of(2023, 6, 25, 15, 30),
    createUser = "user1",
    branch = branch3,
    checkout = true
)

// 커밋 리스트 생성
val commitList = mutableListOf(commit1, commit2, commit3, commit4, commit5, commit6)
