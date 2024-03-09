package com.example.datahub_back.service.backDataService

import com.example.datahub_back.controller.toolController.data.DataResponse
import com.example.datahub_back.data.toolData.exampleDataList
import com.example.datahub_back.dto.toolDTO.Data
import org.springframework.stereotype.Service

@Service
class DataService {

    private val dataList = exampleDataList

    fun getAllData(): List<Data> = dataList

    fun getDataByColumn(id: Long): List<DataResponse> {
        val filteredData: MutableList<DataResponse> = mutableListOf()

        val columnData = exampleDataList.filter { it.column.id == id }

        columnData.forEach {
            filteredData.add(
                    DataResponse(
                        id = it.id,
                        data = it.data,
                        columnLine = it.columLine
                    )
            )
        }

        return filteredData
    }

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

    fun getDataByColumns(columnIDs: List<Long>) = dataList.filter {
        it.column.id in columnIDs
    }
}
