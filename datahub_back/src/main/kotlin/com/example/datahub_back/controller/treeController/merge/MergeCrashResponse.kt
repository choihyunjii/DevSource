package com.example.datahub_back.controller.treeController.merge

import com.example.datahub_back.dto.treeDTO.ChangeData

data class MergeCrashResponse(
    val tableName: String,
    val data: MutableList<MergeCrashColumn>,
)

data class MergeCrashColumn(
    val columnName: String,
    val data: MutableList<MergeCrashData>,
)

data class MergeCrashData(
    val check: ChangeData,
    val target: ChangeData,
)
