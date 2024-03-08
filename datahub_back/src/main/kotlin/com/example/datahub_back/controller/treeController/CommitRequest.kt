package com.example.datahub_back.controller.treeController


import com.example.datahub_back.dto.toolDTO.Profile
import com.example.datahub_back.dto.toolDTO.Project

data class CommitRequest(
    val project: Project,
    val profile: Profile,
    val comment: String,
)

