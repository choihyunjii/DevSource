package com.example.datahub_back.controller.toolController.table

import com.example.datahub_back.dto.toolDTO.dataDTO.CreateDataDTO
import com.example.datahub_back.dto.toolDTO.dataDTO.DeleteDataDTO

data class TableModifiedRequest (
    val tableID  : Long,
    val createData : List<CreateDataDTO>?,
    val updateData : List<DeleteDataDTO>?,
    val deleteRow : List<Long>?
)
