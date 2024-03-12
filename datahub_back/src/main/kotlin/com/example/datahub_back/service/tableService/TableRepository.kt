package com.example.datahub_back.service.tableService

import com.example.datahub_back.controller.toolController.table.TableResponse
import com.example.datahub_back.controller.toolController.table.TableStatusResponse

interface TableRepository {
    fun getTable(tableID: Long) : TableResponse
    fun findTableStatusByDatabaseID(dataBaseID: Long) : TableStatusResponse

}