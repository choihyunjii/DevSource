package com.example.datahub_back.data.treeData

import com.example.datahub_back.dto.treeDTO.ChangePage

val changePage1 = ChangePage(
    pageId = 1,
    pageName = "페이지1",
    path = "C:\\ProgramData",
    commit = commit1
)

val changePage2 = ChangePage(
    pageId = 2,
    pageName = "페이지2",
    path = "C:\\ProgramData",
    commit = commit1
)

val changePageList = mutableListOf(changePage1, changePage2)
