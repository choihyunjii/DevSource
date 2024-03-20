package com.example.datahub_back.controller.treeController.merge

import com.example.datahub_back.dto.treeDTO.ChangeColumn
import com.example.datahub_back.dto.treeDTO.ChangeData

data class MergeCrashResponse(
    val tableName: String,
    val columns: MutableList<ChangeColumn>,
    val data: MutableList<MergeCrashData>,
)

data class MergeCrashData(
    val target: MutableList<ChangeData>,
    val check: MutableList<ChangeData>,
)
