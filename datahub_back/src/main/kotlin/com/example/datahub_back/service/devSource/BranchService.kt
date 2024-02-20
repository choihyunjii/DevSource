package com.example.datahub_back.service.devSource

import com.example.datahub_back.data.devTree.branchList
import com.example.datahub_back.dto.devTree.Branch
import org.springframework.stereotype.Service

@Service
class BranchService {
    // 브랜치 뽑아오기
    fun findByProjectIdAndUserId(userId: String, projectId: Int): Branch? {
        return branchList.find { it.projectId == projectId && it.userId == userId }
    }
}