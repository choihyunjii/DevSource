package com.example.datahub_back.service.treeService

import com.example.datahub_back.dto.toolDTO.Project
import com.example.datahub_back.dto.treeDTO.ChangeTable
import com.example.datahub_back.dto.treeDTO.Commit
import com.example.datahub_back.service.backDataService.ProjectService
import org.springframework.stereotype.Service

@Service
class HistoryService (
    private val projectService: ProjectService,
    private val branchService: BranchService,
    private val sourceCommitService: SourceCommitService
) {
    fun retrieveProjects(userId: Long): List<Project> =
        projectService.getProjectsByTeamProfile(userId)

    fun retrieveCommits(userId: Long, projectId: Long): List<Commit> {
        val branch = branchService.getBranchByUserIdAndProjectId(userId, projectId)
        if (branch != null)
            return sourceCommitService.getCommitsByBranch(branch)
        return emptyList()
    }

    fun retrieveChangeTables(commitId: Int): List<ChangeTable>? =
        sourceCommitService.getChangeTablesByCommitId(commitId)

    fun retrieveChangePages(commitId: Int): List<Long>? =
        sourceCommitService.getChangePagesByCommitId(commitId)


}