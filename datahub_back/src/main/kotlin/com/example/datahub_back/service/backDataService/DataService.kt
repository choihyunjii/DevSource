package com.example.datahub_back.service.backDataService

import com.example.datahub_back.data.devTool.exampleDataList
import com.example.datahub_back.dto.devTool.Column
import com.example.datahub_back.dto.devTool.Data
import org.springframework.stereotype.Service

@Service
class DataService {

    private val dataList = exampleDataList

    fun getAllData(): List<Data> = dataList

    fun getDataByColumn(column: Column) = dataList.filter { it.column == column }
    fun getDataById(id: Long): Data? = dataList.find { it.id == id }

    fun createData(data: Data): Data {
        dataList.add(data)
        return data
    }

    fun updateData(id: Long, newData: Data): Data? {
        val index = dataList.indexOfFirst { it.id == id }
        return if (index != -1) {
            dataList[index] = newData
            newData
        } else {
            null
        }
    }

    fun deleteData(id: Long): Data? {
        val index = dataList.indexOfFirst { it.id == id }
        return if (index != -1) {
            dataList.removeAt(index)
        } else {
            null
        }
    }
}
