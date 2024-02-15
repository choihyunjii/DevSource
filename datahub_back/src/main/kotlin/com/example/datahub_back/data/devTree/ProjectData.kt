package com.example.datahub_back.data.devTree

import com.example.datahub_back.dto.devTree.Project
import java.time.LocalDateTime
import kotlin.collections.mutableListOf

val project1 = Project(
    projectId = 1,
    name = "Project A",
    comment = "This is project A",
    createUser = user1.userId,
    createTime = LocalDateTime.now(),
    isFavorite = 1,
    isDelete = 0,
    teamUsers = mutableListOf(user1, user3)
)

val project2 = Project(
    projectId = 2,
    name = "Project B",
    comment = "This is project B",
    createUser = user2.userId,
    createTime = LocalDateTime.now(),
    isFavorite = 0,
    isDelete = 0,
    teamUsers = mutableListOf(user2, user3)
)

val projectList = listOf(project1, project2)