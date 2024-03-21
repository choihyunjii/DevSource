package com.example.datahub_back.dto.toolDTO

import java.time.LocalDateTime

data class Table(
    val id : Long,
    val name : String,
    val comment : String,
    val isFavorite : Int, //0이면 즐겨찾기 X
    val isDelete : Int,  //0이면 삭제
    val dataBase: DataBase,
    val updateTime: LocalDateTime,
    val tableHash : String
)