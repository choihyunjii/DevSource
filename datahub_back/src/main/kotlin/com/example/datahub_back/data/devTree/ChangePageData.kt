package com.example.datahub_back.data.devTree

import com.example.datahub_back.dto.devTree.ChangePage

val changePage1 = ChangePage(
    pageId = 1,
    pageName = "페이지1",
    path = "C:\\ProgramData"
)

val changePage2 = ChangePage(
    pageId = 2,
    pageName = "페이지2",
    path = "C:\\ProgramData"
)

val changePageList = listOf(changePage1, changePage2)
