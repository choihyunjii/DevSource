package com.example.datahub_back.dto

import java.text.DateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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