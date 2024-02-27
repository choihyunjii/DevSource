package com.example.datahub_back.service.treeService

import com.example.datahub_back.data.treeData.changePageList
import com.example.datahub_back.dto.treeDTO.ChangePage
import org.springframework.stereotype.Service

@Service
class ChangePageService {
    fun createChangePage(changePage: ChangePage): ChangePage {
        changePageList.add(changePage)
        return changePage
    }
}