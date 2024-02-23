package com.example.datahub_back.service.treeService

import com.example.datahub_back.data.treeData.branchList
import com.example.datahub_back.dto.treeDTO.Branch
import org.springframework.stereotype.Service

@Service
class BranchService {
    // 브랜치 뽑아오기
    fun getBranchByUserIdAndProjectId(userId: String, projectId: Long): Branch? =
        branchList.find { it.projectId == projectId && it.userId == userId }

    // 메인 브랜치 찾기
    fun getMainBranchByProjectId(projectId: Long): Branch? {
        return branchList.find { it.projectId == projectId && it.isMainBranch == 1 }
    }

    // 브랜치 push 업데이트
    fun updatePushByBranchId(branchId: Long): Branch? {
        val branch = branchList.find { it.branchId == branchId }
        if (branch != null) {
            branch.push++
            return branch
        }
        return null
    }
}