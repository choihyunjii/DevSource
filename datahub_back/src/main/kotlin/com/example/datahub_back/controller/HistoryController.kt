package com.example.datahub_back.controller

import com.example.datahub_back.dto.devTree.ChangePage
import com.example.datahub_back.dto.devTree.ChangeTable
import com.example.datahub_back.dto.devTree.Commit
import com.example.datahub_back.dto.devTree.Project
import com.example.datahub_back.service.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/history")
class HistoryController(
    private val projectService: ProjectService,
    private val branchService: BranchService,
    private val commitService: CommitService
    ) {

    // 프로젝트 목록
    @GetMapping("/{userId}")
    fun retrieveProjects(@PathVariable userId: String): List<Project> {
        return projectService.findByUserId(userId)
    }

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
    fun findChangePagesByCommitId(@PathVariable commitId: Int): MutableList<ChangePage>? {
        return commitService.findChangePagesByCommitId(commitId)
    }

    // 백엔드 변경 리스트 뽑기
    @GetMapping("/backendChanges/{commitId}")
    fun findChangeTablesByCommitId(@PathVariable commitId: Int): MutableList<ChangeTable>? {
        return commitService.findChangeTablesByCommitId(commitId)
    }
}