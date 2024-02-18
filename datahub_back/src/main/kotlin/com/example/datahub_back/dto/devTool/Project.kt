package com.example.datahub_back.dto.devTool

import java.time.LocalDateTime

data class Project(
    val name : String,
    val comment : String,
    val createTime : LocalDateTime,
    val updateTime : LocalDateTime,
    val isFavorite : Int,
    val isDelete : Int,
    val frontFile : String,
    val dataBase : MutableList<DataBase>,
    val teamProfile : MutableList<Profile>
)