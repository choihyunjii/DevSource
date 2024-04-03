package com.example.datahub_back.service.treeService.tableColumnData

import com.example.datahub_back.data.treeData.changeTableList
import com.example.datahub_back.dto.treeDTO.ChangeTable
import com.example.datahub_back.dto.treeDTO.Commit
import org.springframework.stereotype.Service

@Service
class ChangeTableService {
    fun getTableById(tableId: Long): ChangeTable? =
        changeTableList.find { it.tableId == tableId }

    fun getTablesByCommit(commit: Commit): List<ChangeTable> =
        changeTableList.filter { it.commit == commit }

    // 삭제 여부로 테이블 리스트 가져오기
    fun getTablesByIsDelete(tables: List<ChangeTable>, isDelete: Int): List<ChangeTable> {
        return changeTableList.filter { it.isDelete == isDelete }
    }

    fun createTable(sourceTable: ChangeTable): ChangeTable {
        changeTableList.add(sourceTable)
        return sourceTable
    }

    fun getTableMaxId(): Long = changeTableList.maxByOrNull { it.tableId }?.tableId ?: 1

}