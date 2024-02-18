package com.example.datahub_back.service

import com.example.datahub_back.data.branchList
import com.example.datahub_back.dto.devTree.Branch
import com.example.datahub_back.dto.devTree.Project
import com.example.datahub_back.dto.devTree.User
import org.springframework.stereotype.Service

@Service
class BranchService {
    // 브랜치 뽑아오기
    fun findByProjectIdAndUserId(userId: String, projectId: Int): Branch? {
        return branchList.find { it.projectId == projectId && it.userId == userId }
    }
}