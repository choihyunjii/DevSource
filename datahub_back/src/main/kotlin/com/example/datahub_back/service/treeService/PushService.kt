package com.example.datahub_back.service.treeService

import com.example.datahub_back.dto.treeDTO.Branch
import com.example.datahub_back.dto.treeDTO.Commit
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PushService(
    private val branchService: BranchService,
    private val sourceCommitService: SourceCommitService
) {
    @Transactional(rollbackFor = [RuntimeException::class])
    fun handlePush(projectId: Long, userId: String): String {
        try {
            val mainBranch = branchService.getMainBranchByProjectId(projectId)
            val userBranch = branchService.getBranchByUserIdAndProjectId(userId, projectId)

            if (mainBranch != null && userBranch != null) {
                val pushCount = userBranch.push
                val differentCommits = findDifferentCommits(mainBranch, userBranch)
                    .filter { it.branchId == mainBranch.branchId }

                saveCommits(differentCommits)
                updateBranchAction(userBranch, projectId, pushCount)
            }
            return "Push processed successfully"
        } catch (e: Exception) {
            throw RuntimeException("Error occurred during push: ${e.message}")
        }
    }

    private fun saveCommits(commits: List<Commit>) {
        commits.forEach { sourceCommitService.createCommit(it) }
    }

    // push, pull crash 업데이트
    private fun updateBranchAction(userBranch: Branch, projectId: Long, pushCount: Int) {
        // 다른 브랜치 pull ++
        val filterBranchList = branchService.filterBranchList(userBranch, projectId)
        filterBranchList.forEach { branchService.updatePullByBranchId(it.branchId, pushCount) }
        // 해당 유저 push 리셋
        branchService.updatePushResetByBranchId(userBranch.branchId)
    }

    // 메인과 유저 브랜치의 Commit 차집합
    private fun findDifferentCommits(branch1: Branch, branch2: Branch): List<Commit> {
        val commits1 = sourceCommitService.getCommitsByBranch(branch1.branchId)
        val commits2 = sourceCommitService.getCommitsByBranch(branch2.branchId)

        return (commits1.subtract(commits2.toSet())
                + commits2.subtract(commits1.toSet())).toList()
    }
}
