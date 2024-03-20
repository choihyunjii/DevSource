package com.example.datahub_back.controller.tempelate

data class TemplateListRequest(
    val tableID : Long,
    val menuColumns : List<String>
)
