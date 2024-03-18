package com.example.datahub_back.controller.toolController.table

import com.example.datahub_back.dto.toolDTO.Column
import com.example.datahub_back.dto.toolDTO.Data
import com.example.datahub_back.dto.toolDTO.dataDTO.CreateDataDTO
import com.example.datahub_back.dto.toolDTO.dataDTO.DeleteDataDTO

data class TableModifiedRequest (
    val tableID  : Long,
    val createData : List<CreateDataDTO>?,
    val updateData : List<Data>?,
    val deleteRow : List<Long>?
)
