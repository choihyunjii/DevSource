package com.example.datahub_back.service.devTree

import com.example.datahub_back.data.devTree.changeTableList
import com.example.datahub_back.dto.devTree.ChangeTable
import org.springframework.stereotype.Service

@Service
class ChangeTableService {
    fun createChangeTable(changeTable: ChangeTable): ChangeTable {
        changeTableList.add(changeTable)
        return changeTable
    }
}