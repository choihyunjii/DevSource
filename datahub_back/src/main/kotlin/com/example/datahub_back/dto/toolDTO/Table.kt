package com.example.datahub_back.dto.toolDTO

data class Table(
    var id : Long,
    val name : String,
    val comment : String,
    val isFavorite : Int, //0이면 즐겨찾기 X
    val isDelete : Int,  //0이면 삭제
    val dataBase: DataBase
)