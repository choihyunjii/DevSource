package com.example.datahub_back.service.treeService.checkout

import com.example.datahub_back.dto.treeDTO.Commit
import com.example.datahub_back.service.treeService.BranchService
import com.example.datahub_back.service.treeService.CommitService
import org.springframework.stereotype.Service

@Service
class CheckoutService(
    private val commitService: CommitService,
    private val branchService: BranchService,
) {
    fun checkoutCommit(commitId: Int, branchId: Long): Commit? {
        val commit = commitService.getCommitByCommitId(commitId)
        val branch = branchService.getBranchByBranchId(branchId)
        if (branch != null) {
            commitService.uncheckCommitsForBranch(branch)
        }
        if (commit != null) {
            commit.checkout = true
        }
        return commit
    }
}