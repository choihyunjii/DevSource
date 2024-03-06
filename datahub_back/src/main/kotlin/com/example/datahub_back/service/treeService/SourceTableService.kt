package com.example.datahub_back.service.treeService

import com.example.datahub_back.data.treeData.sourceTableList
import com.example.datahub_back.dto.treeDTO.SourceTable
import org.springframework.stereotype.Service

@Service
class SourceTableService {

    private val tables = sourceTableList

    fun getTableById(id: Long): SourceTable? =
        tables.find { it.tableId == id }

    fun getTableByTableIds(tableIds: List<Long>): List<SourceTable> =
        tables.filter { it.tableId in tableIds }

    fun createTable(sourceTable: SourceTable): SourceTable {
        sourceTable.tableId = (tables.maxByOrNull { it.tableId }?.tableId ?: 0) + 1
        tables.add(sourceTable)
        return sourceTable
    }

    // 테이블 수정
    fun updateTable(id: Long, newSourceTable: SourceTable): SourceTable? {
        val index = tables.indexOfFirst { it.tableId == id }
        if (index != -1) {
            tables[index] = newSourceTable.copy(tableId = id)
            return tables[index]
        }
        return null
    }

    // 테이블 삭제
    fun deleteTable(id: Long) = tables.removeIf { it.tableId == id }

}