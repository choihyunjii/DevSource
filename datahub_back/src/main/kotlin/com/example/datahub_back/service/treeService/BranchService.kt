package com.example.datahub_back.service.treeService

import com.example.datahub_back.data.treeData.branchList
import com.example.datahub_back.dto.toolDTO.Profile
import com.example.datahub_back.dto.toolDTO.Project
import com.example.datahub_back.dto.treeDTO.Branch
import org.springframework.stereotype.Service

@Service
class BranchService {
    // 브랜치 뽑아오기
    fun getBranchByProject(project: Project): Branch? =
        branchList.find { it.project == project }

    fun getBranchByBranchId(branchId: Long): Branch? =
        branchList.find { it.branchId == branchId}

    fun getBranchByUserAndProject(user: Profile, project: Project): Branch? =
        branchList.find { it.project == project && it.profile == user }

    fun getBranchByUserIdAndProjectId(userId: Long, projectId: Long): Branch? =
        branchList.find { it.project.id == projectId && it.profile?.id == userId }

    // 메인 브랜치 찾기
    fun getMainBranchByProject(project: Project): Branch? {
        return branchList.find { it.project == project && it.isMainBranch == 1 }
    }

    // 브랜치 pullRequest 리셋
    fun pullRequestResetByBranchId(branchId: Long): Branch? {
        val branch = branchList.find { it.branchId == branchId }
        if (branch != null) {
            branch.pullRequest = 0
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

    // 메인과 해당 브랜치를 제외한 나머지 브랜치 리스트 찾기
    fun filterBranchList(targetBranch: Branch, project: Project): List<Branch> {
        val branchesWithProjectId = branchList.filter { it.project == project }
        return branchesWithProjectId.filterNot { it == targetBranch || it.isMainBranch == 1 }
    }

    // 해당 브랜치를 제외한 나머지 브랜치 리스트 찾기
    fun getBranchesNotMainByProject(project: Project): List<Branch> {
        val branchesWithProjectId = branchList.filter { it.project == project }
        return branchesWithProjectId.filterNot { it.isMainBranch == 1 }
    }


}