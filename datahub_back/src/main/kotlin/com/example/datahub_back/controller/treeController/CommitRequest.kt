package com.example.datahub_back.controller.treeController


import com.example.datahub_back.dto.treeDTO.*
import java.time.LocalDateTime

data class CommitRequest(
    val commitId: Long,
    val branchId: Long,
    val comment: String,
    val createTime: LocalDateTime,
    val createUser: String,
    val tables: MutableList<SourceTable>,
    val columns: MutableList<SourceColumn>,
    val data: MutableList<SourceData>,
    val changeTables: MutableList<ChangeTable>,
    val changePages: MutableList<ChangePage>
)

