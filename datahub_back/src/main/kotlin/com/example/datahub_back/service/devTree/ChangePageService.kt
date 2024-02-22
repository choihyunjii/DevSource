package com.example.datahub_back.service.devTree

import com.example.datahub_back.data.devTree.changePageList
import com.example.datahub_back.dto.devTree.ChangePage
import org.springframework.stereotype.Service

@Service
class ChangePageService {
    fun createChangePage(changePage: ChangePage): ChangePage {
        changePageList.add(changePage)
        return changePage
    }
}