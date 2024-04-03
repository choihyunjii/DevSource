package com.example.datahub_back.service.treeService.pullRequest

import com.example.datahub_back.dto.toolDTO.Profile
import com.example.datahub_back.dto.toolDTO.Project
import com.example.datahub_back.dto.treeDTO.Branch
import com.example.datahub_back.dto.treeDTO.Commit
import com.example.datahub_back.service.treeService.BranchService
import com.example.datahub_back.service.treeService.CommitService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PullRequestService(
    private val branchService: BranchService,
    private val commitService: CommitService
) {
    // 서브 사용자 입장 (수정 필요)
    @Transactional(rollbackFor = [RuntimeException::class])
    fun handlePush(project: Project, user: Profile): String {
        try {
            val mainBranch = branchService.getMainBranchByProject(project)
            val userBranch = branchService.getBranchByUserAndProject(user, project)

            if (mainBranch != null && userBranch != null) {
                val pushCount = userBranch.pullRequest
                val differentCommits = findDifferentCommits(mainBranch, userBranch)
                    .filter { it.branch == mainBranch }

                saveCommits(differentCommits)
            }
            return "Push processed successfully"
        } catch (e: Exception) {
            throw RuntimeException("Error occurred during push: ${e.message}")
        }
    }

    private fun saveCommits(commits: List<Commit>) {
        commits.forEach { commitService.createCommit(it) }
    }

    // push, pull crash 업데이트
//    private fun updateBranchAction(userBranch: Branch, project: Project, pushCount: Int) {
//        // 다른 브랜치 pull ++
//        val filterBranchList = branchService.filterBranchList(userBranch, project)
//        filterBranchList.forEach { branchService.updateBranchPlusByBranchId(it.branchId) }
//        // 해당 유저 push 리셋
//        branchService.pullRequestResetByBranchId(userBranch.branchId)
//    }

    // 메인과 유저 브랜치의 Commit 차집합
    private fun findDifferentCommits(branch1: Branch, branch2: Branch): List<Commit> {
        val commits1 = commitService.getCommitsByBranch(branch1)
        val commits2 = commitService.getCommitsByBranch(branch2)

        return (commits1.subtract(commits2.toSet())
                + commits2.subtract(commits1.toSet())).toList()
    }
}
