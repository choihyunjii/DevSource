package com.example.datahub_back.service

import com.example.datahub_back.data.devTree.branchList
import com.example.datahub_back.dto.devTree.Branch
import com.example.datahub_back.dto.devTree.Project
import com.example.datahub_back.dto.devTree.User

class BranchService {

    // 브랜치 뽑아오기
    fun getBranchByProjectIdAndUserId(projectId: Project, userId: User): Branch? {
        return branchList.find { it.projectId == projectId && it.userId == userId }
    }
}