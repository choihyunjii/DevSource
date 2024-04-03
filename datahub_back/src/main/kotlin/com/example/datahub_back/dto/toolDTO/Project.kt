package com.example.datahub_back.dto.toolDTO

import java.time.LocalDateTime

data class Project(
    var id: Long?,
    val name: String,
    val comment: String,
    val createTime: LocalDateTime,
    val updateTime: LocalDateTime,
    val isFavorite: Int?,
    val isDelete: Int?,
    val teamProfile: MutableList<Profile>,
    val createProfile: Profile
)