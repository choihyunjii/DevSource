package com.example.datahub_back.service.treeService.tableColumnData

import com.example.datahub_back.data.treeData.changeDataList
import com.example.datahub_back.dto.treeDTO.ChangeColumn
import com.example.datahub_back.dto.treeDTO.ChangeData
import com.example.datahub_back.dto.treeDTO.SourceColumn
import com.example.datahub_back.dto.treeDTO.SourceData
import org.springframework.stereotype.Service

@Service
class ChangeDataService {

    private val dataList = changeDataList

    fun getAllData(): List<ChangeData> = dataList

    fun getDataListByColumn(column: ChangeColumn) = dataList.filter { it.column == column }

    fun getDataById(id: Long): ChangeData? = dataList.find { it.dataId == id }

    fun createData(data: ChangeData): ChangeData {
        dataList.add(data)
        return data
    }

    fun findDataListByColumnAndLine(columns: List<ChangeColumn>, columnLine: Int): List<ChangeData>? {
        return dataList.filter { it.column in columns && it.columnLine == columnLine }
    }
}
