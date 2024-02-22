package com.example.datahub_back.data.devTree
import com.example.datahub_back.dto.devTree.SourceProject
import java.time.LocalDateTime
import kotlin.collections.mutableListOf

val sourceProject1 = SourceProject(
    projectId = 1,
    projectName = "Project A",
    comment = "This is project A",
    createUser = sourceUser1.userId,
    createTime = LocalDateTime.now(),
    isFavorite = 1,
    isDelete = 0,
    teamSourceUsers = mutableListOf(sourceUser1, sourceUser3)
)

val sourceProject2 = SourceProject(
    projectId = 2,
    projectName = "Project B",
    comment = "This is project B",
    createUser = sourceUser2.userId,
    createTime = LocalDateTime.now(),
    isFavorite = 0,
    isDelete = 0,
    teamSourceUsers = mutableListOf(sourceUser2, sourceUser3)
)

val projectList = listOf(sourceProject1, sourceProject2)