package com.example.datahub_back.service.devTree

import com.example.datahub_back.data.devTree.branchList
import com.example.datahub_back.dto.devTree.Branch
import org.springframework.stereotype.Service

@Service
class BranchService {
    // 브랜치 뽑아오기
    fun findByProjectIdAndUserId(userId: String, projectId: Int): Branch? =
        branchList.find { it.projectId == projectId && it.userId == userId }

    // 브랜치 push 업데이트
    fun updatePushByBranchId(branchId: Int): Branch? {
        val branch = branchList.find { it.branchId == branchId }
        if (branch != null) {
            branch.push++
            return branch
        }
        return null
    }

}