package com.example.datahub_back.controller.toolController.project

import com.example.datahub_back.dto.toolDTO.Profile
import java.time.LocalDateTime

data class ProjectResponse(
    val projectName: String,
    val comment: String,
    val createTime: LocalDateTime,
    val profile : Profile,
    val teamProfile : List<Profile>
)
