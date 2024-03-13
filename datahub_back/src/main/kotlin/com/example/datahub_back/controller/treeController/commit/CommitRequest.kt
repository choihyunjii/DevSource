package com.example.datahub_back.controller.treeController.commit


import com.example.datahub_back.dto.toolDTO.Profile
import com.example.datahub_back.dto.toolDTO.Project

data class CommitRequest(
    val projectId: Long,
    val profileId: Long,
    val comment: String,
)

