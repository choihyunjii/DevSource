package com.example.datahub_back.dto.toolDTO

import com.example.datahub_back.dto.treeDTO.SourceTable


data class Column(
    val id : Long,
    val name : String,
    val comment : String,
    val dataType : String,
    val isPrimaryKey : Int,
    val isForeignKey : Int,
    val isUniqueKey : Int,
    val table: Table,
    val joinSourceTable: SourceTable?
)
