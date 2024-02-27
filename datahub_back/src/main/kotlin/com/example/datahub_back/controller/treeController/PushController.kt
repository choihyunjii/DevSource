package com.example.datahub_back.controller.treeController

import com.example.datahub_back.dto.treeDTO.Branch
import com.example.datahub_back.dto.treeDTO.Commit
import com.example.datahub_back.service.treeService.BranchService
import com.example.datahub_back.service.treeService.CommitService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/push")
class PushController(
    private val branchService: BranchService,
    private val commitService: CommitService,
) {
    @GetMapping("/{projectId}/{userId}")
    @Transactional(rollbackFor = [RuntimeException::class])
    fun handlePush(@PathVariable projectId: Long, @PathVariable userId: String): ResponseEntity<String> {
        return try {
            val mainBranch = branchService.getMainBranchByProjectId(projectId)
            val userBranch = branchService.getBranchByUserIdAndProjectId(userId, projectId)

            if (mainBranch != null && userBranch != null) {
                // 커밋 리스트 뽑아와서 브랜치를 메인으로 바꾸기
                val differentCommits = findDifferentCommits(mainBranch, userBranch)
                    .filter { it.branchId == mainBranch.branchId }

                saveCommits(differentCommits)
            }
            ResponseEntity.ok("Push processed successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: ${e.message}")
        }
    }

    // 커밋 생성
    private fun saveCommits(commits: List<Commit>) {
        for (commit in commits) {
            commitService.createCommit(commit)
        }
    }

    // 커밋 비교해서 다른 커밋 리스트 뽑기
    private fun findDifferentCommits(mainBranch: Branch, userBranch: Branch): List<Commit> {
        val mainBranchCommits = commitService.getCommitsByBranch(mainBranch.branchId)
        val userBranchCommits = commitService.getCommitsByBranch(userBranch.branchId)

        return (mainBranchCommits.subtract(userBranchCommits.toSet())
                + userBranchCommits.subtract(mainBranchCommits.toSet())).toList()
    }
}
