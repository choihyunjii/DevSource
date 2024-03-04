package com.example.datahub_back.service.treeService

import com.example.datahub_back.dto.toolDTO.Project
import com.example.datahub_back.dto.treeDTO.Commit
import com.example.datahub_back.service.backDataService.DevProjectService
import org.springframework.stereotype.Service

@Service
class HistoryService (
    private val devProjectService: DevProjectService,
    private val branchService: BranchService,
    private val sourceCommitService: SourceCommitService
) {
    fun retrieveProjects(userId: Long): List<Project> =
        devProjectService.getProjectsByTeamProfile(userId)

    fun retrieveCommits(userId: String, projectId: Long): List<Commit> {
        val branch = branchService.getBranchByUserIdAndProjectId(userId, projectId)
        if (branch != null)
            return sourceCommitService.getCommitsByBranch(branch.branchId)
        return emptyList()
    }

    fun retrieveChangePageIds(commitId: Long): MutableList<Long>? =
        sourceCommitService.getChangePagesByCommitId(commitId)

    fun retrieveChangeTableIds(commitId: Long): MutableList<Long>? =
        sourceCommitService.getChangeTablesByCommitId(commitId)
}