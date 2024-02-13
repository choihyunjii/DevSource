package com.example.datahub_back.dto

import javax.sql.rowset.spi.SyncResolver

data class DataBase(
    val name : String,
    val comment : String,
    val isFavorite : Int, //0이면 즐겨찾기 X
    val isDelete : Int,  //0이면 삭제
    val columList : MutableList<Colum>
)
