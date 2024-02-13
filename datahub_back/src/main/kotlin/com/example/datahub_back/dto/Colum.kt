package com.example.datahub_back.dto

data class Colum(
    val name : String,
    val comment : String,
    val dataType : String,

    val isPrimaryKey : Int,
    val isForeignKey : Int,
    val isUniqueKey : Int,

    val dataList : List<Data>
)