package com.example.datahub_back.service.treeService

import com.example.datahub_back.data.treeData.changeTableList
import com.example.datahub_back.dto.treeDTO.ChangeTable
import org.springframework.stereotype.Service

@Service
class ChangeTableService {
    fun createChangeTable(changeTable: ChangeTable): ChangeTable {
        changeTableList.add(changeTable)
        return changeTable
    }
}