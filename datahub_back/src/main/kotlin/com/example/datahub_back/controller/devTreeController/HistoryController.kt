package com.example.datahub_back.controller.devTreeController

import com.example.datahub_back.dto.devTree.ChangePage
import com.example.datahub_back.dto.devTree.ChangeTable
import com.example.datahub_back.dto.devTree.Commit
import com.example.datahub_back.dto.devTree.Project
import com.example.datahub_back.service.devTree.BranchService
import com.example.datahub_back.service.devTree.CommitService
import com.example.datahub_back.service.devTree.ProjectService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/history")
class HistoryController(
    private val projectService: ProjectService,
    private val branchService: BranchService,
    private val commitService: CommitService
    ) {

    // 프로젝트 목록
    @GetMapping("/{userId}")
    fun retrieveProjects(@PathVariable userId: String): List<Project> =
        projectService.findByUserId(userId)

    // 커밋 목록
    @GetMapping("/{userId}/{projectId}")
    fun retrieveCommits(@PathVariable userId: String, @PathVariable projectId: Int): List<Commit> {
        val branch = branchService.findByProjectIdAndUserId(userId, projectId)
        if (branch != null)
            return commitService.findByBranch(branch.branchId)
        return emptyList()
    }

    // 프론트 변경 리스트 뽑기
    @GetMapping("/frontChanges/{commitId}")
    fun retrieveChangePages(@PathVariable commitId: Int): MutableList<ChangePage>? =
        commitService.findChangePagesByCommitId(commitId)

    // 백엔드 변경 리스트 뽑기
    @GetMapping("/backendChanges/{commitId}")
    fun retrieveChangeTables(@PathVariable commitId: Int): MutableList<ChangeTable>? =
        commitService.findChangeTablesByCommitId(commitId)

}