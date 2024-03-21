package com.example.datahub_back.service.treeService.tableColumnData

import com.example.datahub_back.data.treeData.sourceDataList
import com.example.datahub_back.dto.treeDTO.SourceColumn
import com.example.datahub_back.dto.treeDTO.SourceData
import org.springframework.stereotype.Service

@Service
class SourceDataService {

    private val dataList = sourceDataList

    fun getAllData(): List<SourceData> = dataList

    fun getDataListByColumn(column: SourceColumn) = dataList.filter { it.column == column }

    fun getDataListByColumns(columns: List<SourceColumn>) = dataList.filter { data ->
        columns.any { column -> data.column == column }
    }

    fun findDataListByColumnAndLine(columns: List<SourceColumn>, columnLine: Int): List<SourceData>? {
        return dataList.filter { it.column in columns && it.columnLine == columnLine }
    }

    fun getDataById(id: Long): SourceData? = dataList.find { it.dataId == id }

    fun createData(data: SourceData): SourceData {
        dataList.add(data)
        return data
    }

    fun getDataMaxId() = dataList.maxByOrNull { it.dataId }?.dataId ?: 1

}
