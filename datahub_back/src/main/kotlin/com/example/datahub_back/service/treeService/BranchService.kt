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

    // 브랜치 push ++ 업데이트
    fun updatePushCountByBranchId(branchId: Long): Branch? {
        val branch = branchList.find { it.branchId == branchId }
        if (branch != null) {
            branch.push++
            return branch
        }
        return null
    }

    // 브랜치 push 리셋
    fun updatePushResetByBranchId(branchId: Long): Branch? {
        val branch = branchList.find { it.branchId == branchId }
        if (branch != null) {
            branch.push = 0
            return branch
        }
        return null
    }

    // 브랜치 pull 업데이트
    fun updatePullByBranchId(branchId: Long, pullIncrement: Int): Branch? {
        val branch = branchList.find { it.branchId == branchId }
        if (branch != null) {
            branch.pull += pullIncrement
            return branch
        }
        return null
    }

    // 브랜치 pull 리셋
    fun updatePullResetByBranchId(branchId: Long): Branch? {
        val branch = branchList.find { it.branchId == branchId }
        if (branch != null) {
            branch.pull = 0
            return branch
        }
        return null
    }

    // 브랜치 crash 업데이트
    fun updateCrashByBranchId(branchId: Long, crashIncrement: Int): Branch? {
        val branch = branchList.find { it.branchId == branchId }
        if (branch != null) {
            branch.crash += crashIncrement
            return branch
        }
        return null
    }

    // 브랜치 crash --
    fun updateCrashDecreaseByBranchId(branchId: Long): Branch? {
        val branch = branchList.find { it.branchId == branchId }
        if (branch != null) {
            branch.crash--
            return branch
        }
        return null
    }

    // 메인과 해당 유저 브랜치를 제외한 나머지 브랜치 리스트 찾기
    fun filterBranchList(targetBranch: Branch, projectId: Long): List<Branch> {
        val branchesWithProjectId = branchList.filter { it.projectId == projectId }
        return branchesWithProjectId.filterNot { it == targetBranch || it.isMainBranch == 1 }
    }
}