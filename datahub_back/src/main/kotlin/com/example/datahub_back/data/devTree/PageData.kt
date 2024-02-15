package com.example.datahub_back.data.devTree

import com.example.datahub_back.dto.devTree.Page
import java.time.LocalDateTime

val page1 = Page(
    pageId = 1,
    commitId = commit1,
    path = "/pages/page1",
    pageName = "페이지1",
    isFavorite = 1,
    isDelete = 0,
    updateTime = LocalDateTime.now()
)