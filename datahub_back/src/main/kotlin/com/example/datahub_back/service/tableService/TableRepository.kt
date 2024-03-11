package com.example.datahub_back.service.tableService

import com.example.datahub_back.dto.toolDTO.Column
import com.example.datahub_back.dto.toolDTO.Data
import com.example.datahub_back.dto.toolDTO.Table

interface TableRepository {
    //Table을 형성하기 가장 좋은 방법은 뭘까?

    fun createTable(columns : List<Column>) : MutableMap<String, List<Data>>

    fun findColumnsByTableID(table : Table) : List<Column>
}