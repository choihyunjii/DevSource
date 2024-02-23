package com.example.datahub_back.service.treeService

import com.example.datahub_back.data.treeData.sourceTableList
import com.example.datahub_back.dto.treeDTO.SourceTable
import org.springframework.stereotype.Service

@Service
class SourceTableService {

    private val tables = sourceTableList

    fun getTableById(id: Long): SourceTable? =
        tables.find { it.tableId == id }


    fun createTable(table: SourceTable): SourceTable {
        table.tableId = (tables.maxByOrNull { it.tableId }?.tableId ?: 0) + 1
        tables.add(table)
        return table
    }

    // 테이블 수정
    fun updateTable(id: Long, newTable: SourceTable): SourceTable? {
        val index = tables.indexOfFirst { it.tableId == id }
        if (index != -1) {
            tables[index] = newTable.copy(tableId = id)
            return tables[index]
        }
        return null
    }

    // 테이블 삭제
    fun deleteTable(id: Long) = tables.removeIf { it.tableId == id }

}