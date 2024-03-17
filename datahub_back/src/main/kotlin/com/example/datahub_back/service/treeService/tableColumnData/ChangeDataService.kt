package com.example.datahub_back.service.treeService.tableColumnData

import com.example.datahub_back.data.treeData.changeDataList
import com.example.datahub_back.dto.treeDTO.ChangeColumn
import com.example.datahub_back.dto.treeDTO.ChangeData
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

    fun updateData(id: Long, newData: ChangeData): ChangeData? {
        val index = dataList.indexOfFirst { it.dataId == id }
        return if (index != -1) {
            dataList[index] = newData
            newData
        } else {
            null
        }
    }

    fun deleteData(id: Long): ChangeData? {
        val index = dataList.indexOfFirst { it.dataId == id }
        return if (index != -1) {
            dataList.removeAt(index)
        } else {
            null
        }
    }
}
