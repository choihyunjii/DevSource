package com.example.datahub_back.controller.toolController.table
import com.example.datahub_back.dto.toolDTO.Table

data class TableStatusResponse (
    val id : Long, //테이블 ID
    val isDeleteTables : List<Table>, //삭제 테이블
    val isFavoriteTables : List<Table>, //즐겨 찾기 테이블
    val isAllTables : List<Table> , //삭제가 제외된 모든 테이블
)
