package com.example.datahub_back.service.BackDataService

import com.example.datahub_back.data.devTool.exampleDataBaseList
import com.example.datahub_back.dto.devTool.DataBase
import com.example.datahub_back.dto.devTool.Project
import org.springframework.stereotype.Service

@Service
class DataBaseService {

    fun getAllDataBasesByProject(project: Project): List<DataBase> {
        return exampleDataBaseList.filter { it.project == project }
    }

    fun getDataBaseById(id: Long): DataBase? {
        return exampleDataBaseList.find { it.id == id }
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
