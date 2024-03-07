package com.example.datahub_back.service.backDataService

import com.example.datahub_back.data.toolData.exampleDataList
import com.example.datahub_back.dto.toolDTO.Column
import com.example.datahub_back.dto.toolDTO.Data
import org.springframework.stereotype.Service

@Service
class DataService {

    private val dataList = exampleDataList

    fun getAllData(): List<Data> = dataList

    fun getDataByColumn(columnID: Long) = dataList.filter { it.column.id == columnID }

    fun getDataByColumns(columnIDs: List<Long>) = dataList.filter { it.column.id in columnIDs }

    fun getDataByColumn(column: Column) = dataList.filter {
        it.column == column
    }[0]

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
