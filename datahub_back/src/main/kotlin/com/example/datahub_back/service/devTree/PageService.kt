package com.example.datahub_back.service.devTree

import com.example.datahub_back.data.devTree.pageList
import com.example.datahub_back.dto.devTree.Page
import org.springframework.stereotype.Service

@Service
class PageService {
    fun createPage(page: Page): Page {
        pageList.add(page)
        return page
    }
}