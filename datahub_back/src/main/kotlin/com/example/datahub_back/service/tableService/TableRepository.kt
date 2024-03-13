package com.example.datahub_back.service.tableService

import com.example.datahub_back.controller.toolController.table.TableModifiedRequest
import com.example.datahub_back.controller.toolController.table.TableResponse
import com.example.datahub_back.controller.toolController.table.TableStatusResponse

interface TableRepository {
    fun getTable(tableID: Long) : TableResponse
    //테이블 가지고 오는 함수
    fun findTableStatusByDatabaseID(dataBaseID: Long) : TableStatusResponse
    //테이블 상태 별로 가지고 오는 함수
    fun modifiedTableAndDataFormatTest(tableModifiedRequest: TableModifiedRequest) : String?
    //테이블 수정 삭제 목록 관리하는 함수
}