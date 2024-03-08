package com.example.datahub_back.service.backDataService

import com.example.datahub_back.data.toolData.exampleDataBaseList
import com.example.datahub_back.dto.toolDTO.DataBase
import com.example.datahub_back.dto.toolDTO.Project
import org.springframework.stereotype.Service

@Service
class DataBaseService {

    fun getAllDataBasesByProject(project: Project): List<DataBase> {
        return exampleDataBaseList.filter { it.project == project }
    }

    fun getDataBaseById(id: Long): DataBase? {
        return exampleDataBaseList.find { it.id == id }
    }

    fun getDataBaseByProject(project: Project): DataBase? {
        return exampleDataBaseList.find { it.project == project }
    }

    fun createDataBase(dataBase: DataBase): DataBase {
        dataBase.id = (exampleDataBaseList.maxByOrNull { it.id }?.id ?: 0) + 1
        exampleDataBaseList.add(dataBase)
        return dataBase
    }

    fun updateDataBase(id: Long, newDatabase: DataBase): DataBase? {
        val index = exampleDataBaseList.indexOfFirst { it.id == id }
        if (index != -1) {
            exampleDataBaseList[index] = newDatabase.copy(id = id)
            return exampleDataBaseList[index]
        }
        return null
    }

    fun deleteDataBase(id: Long): DataBase? {
        val database = exampleDataBaseList.find { it.id == id }
        exampleDataBaseList.removeIf { it.id == id }
        return database
    }
}
