package com.example.datahub_back.service.treeService.commit

import com.example.datahub_back.controller.treeController.commit.CommitRequest
import com.example.datahub_back.dto.toolDTO.Profile
import com.example.datahub_back.dto.toolDTO.Project
import com.example.datahub_back.dto.treeDTO.Branch
import com.example.datahub_back.dto.treeDTO.Commit
import com.example.datahub_back.service.backDataService.ProjectService
import com.example.datahub_back.service.profileService.ProfileService
import com.example.datahub_back.service.treeService.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class TreeCommitService(
    private val projectService: ProjectService,
    private val profileService: ProfileService,
    private val commitService: CommitService,
    private val branchService: BranchService,
    private val changeService: ChangeService,
) {
    // 일반 커밋
    @Transactional(rollbackFor = [RuntimeException::class])
    fun handleCommit(commitData: CommitRequest): String {
        try {
            val project = projectService.getProjectById(commitData.projectId) ?: error("Project not found")
            val profile = profileService.getProfileById(commitData.profileId) ?: error("Profile not found")
            val branch = branchService.getBranchByProject(project) ?: error("Branch not found")

            commitService.uncheckCommitsForBranch(branch) // 체크아웃 초기화
            // 새로운 commit 객체 만들기
            val newCommit = createNewCommit(commitData.comment, project, profile, branch)
            commitService.createCommit(newCommit) // 커밋 추가
            changeService.findChanges(newCommit) // 변경 사항 가져오고 저장

            if (branch.isMainBranch == 1) { // 메인 브랜치라면 다른 브랜치들 update branch 활성화
                val userBranches = branchService.getBranchesNotMainByProject(project)
                userBranches.forEach{ branch -> branch.updateBranch++ }
            } else { // 해당 브랜치의 pullRequest ++
                branch.pullRequest++
            }

        } catch (e: Exception) {
            throw RuntimeException("Error occurred during commit processing: ${e.message}")
        }
        return "All commits processed successfully"
    }

    // 병합 후 커밋
    fun mergingCommit(project: Project, profile: Profile, comment: String): Commit {
        val branch = branchService.getBranchByProject(project) ?: error("Branch not found")
        return createNewCommit(comment, project, profile, branch)
    }

    private fun createNewCommit(comment: String, project: Project, profile: Profile, branch: Branch): Commit {
        val commitHashCode = "${project}${LocalDateTime.now()}".hashCode() // 해시코드 만들기
        return Commit(
            commitId = commitHashCode,
            branch = branch,
            comment = comment,
            createTime = LocalDateTime.now(),
            createUser = profile.username,
            checkout = true,
        )
    }
}

//    [비교 로직 (변경 사항 찾기) 시작 전]
//    1. 해당 Project 객체를 가지고 있는 Branch를 찾음
//    2. 새로운 Commit 객체 생성 후
//    3. Branch와 Commit을 변경 사항 찾기 로직에 전달
