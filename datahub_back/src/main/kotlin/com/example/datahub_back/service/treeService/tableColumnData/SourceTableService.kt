package com.example.datahub_back.service.treeService.tableColumnData

import com.example.datahub_back.data.treeData.sourceTableList
import com.example.datahub_back.dto.treeDTO.Commit
import com.example.datahub_back.dto.treeDTO.SourceTable
import org.springframework.stereotype.Service

@Service
class SourceTableService {

    private val tables = sourceTableList

    fun getTableById(tableId: Long): SourceTable? =
        tables.find { it.tableId == tableId }

    fun getTablesByCommit(commit: Commit): List<SourceTable> =
        tables.filter { it.commit == commit }

    // 삭제 여부로 테이블 리스트 가져오기
    fun getTablesByIsDelete(tables: List<SourceTable>, isDelete: Int): List<SourceTable> {
        return tables.filter { it.isDelete == isDelete }
    }

    fun createTable(sourceTable: SourceTable): SourceTable {
        tables.add(sourceTable)
        return sourceTable
    }

    // 테이블 삭제
    fun deleteTable(id: Long) = tables.removeIf { it.tableId == id }

}