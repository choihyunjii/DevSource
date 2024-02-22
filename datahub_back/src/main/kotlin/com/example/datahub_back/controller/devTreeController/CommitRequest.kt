package com.example.datahub_back.controller.devTreeController

import com.example.datahub_back.dto.devTool.Column
import com.example.datahub_back.dto.devTool.Data
import com.example.datahub_back.dto.devTool.Table
import com.example.datahub_back.dto.devTree.ChangePage
import com.example.datahub_back.dto.devTree.ChangeTable
import com.example.datahub_back.dto.devTree.Page
import java.time.LocalDateTime

data class CommitRequest(
    val commitId: Int,
    val branchId: Int,
    val comment: String,
    val createTime: LocalDateTime,
    val createUser: String,
    val tables: MutableList<Table>,
    val columns: MutableList<Column>,
    val data: MutableList<Data>,
    val pages: MutableList<Page>,
    val changeTables: MutableList<ChangeTable>,
    val changePages: MutableList<ChangePage>
)

