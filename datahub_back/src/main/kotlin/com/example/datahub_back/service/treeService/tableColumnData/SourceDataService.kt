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

    fun getDataById(id: Long): SourceData? = dataList.find { it.dataId == id }

    fun createData(data: SourceData): SourceData {
        dataList.add(data)
        return data
    }

    fun updateData(id: Long, newData: SourceData): SourceData? {
        val index = dataList.indexOfFirst { it.dataId == id }
        return if (index != -1) {
            dataList[index] = newData
            newData
        } else {
            null
        }
    }

    fun deleteData(id: Long): SourceData? {
        val index = dataList.indexOfFirst { it.dataId == id }
        return if (index != -1) {
            dataList.removeAt(index)
        } else {
            null
        }
    }
}
