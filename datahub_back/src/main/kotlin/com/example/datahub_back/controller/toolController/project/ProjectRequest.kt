package com.example.datahub_back.controller.toolController.project

import com.example.datahub_back.dto.toolDTO.Profile

data class ProjectRequest(
    val name : String,
    val comment : String,
    val profile: Profile,
    val dataBaseName : String,
    val teamProfile : MutableList<Profile>
)