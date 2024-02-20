package com.example.datahub_back.service.backDataService
import com.example.datahub_back.data.devTool.exampleTableList
import com.example.datahub_back.dto.devTool.DataBase
import com.example.datahub_back.dto.devTool.Table
import org.springframework.stereotype.Service

@Service
class TableService {

    private val tables = exampleTableList

    // 모든 테이블 가져오기
    fun getTablesByDatabase(dataBase: DataBase): List<Table> {
        return tables.filter { it.dataBase == dataBase }
    }

    fun getTableById(id: Long): Table? =
        tables.find { it.id == id }


    fun createTable(table: Table): Table {
        table.id = (tables.maxByOrNull { it.id }?.id ?: 0) + 1
        tables.add(table)
        return table
    }

    // 테이블 수정
    fun updateTable(id: Long, newTable: Table): Table? {
        val index = tables.indexOfFirst { it.id == id }
        if (index != -1) {
            tables[index] = newTable.copy(id = id)
            return tables[index]
        }
        return null
    }

    // 테이블 삭제
    fun deleteTable(id: Long) = tables.removeIf { it.id == id }

}