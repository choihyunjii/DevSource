package com.example.datahub_back.controller.toolController.table


data class TableResponse (
    val id : Long,
    val table: MutableMap<String, List<com.example.datahub_back.dto.toolDTO.Data>>
)
