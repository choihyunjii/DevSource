package com.example.datahub_back.controller.treeController

import com.example.datahub_back.dto.toolDTO.Profile
import com.example.datahub_back.dto.toolDTO.Project
import com.example.datahub_back.dto.treeDTO.ChangeTable
import com.example.datahub_back.dto.treeDTO.Commit
import com.example.datahub_back.service.treeService.HistoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/history")
class HistoryController(
    private val historyService: HistoryService
    ) {
    // 프로젝트 목록
    @GetMapping("/{userId}")
    fun retrieveProjects(@PathVariable userId: Long): List<Project> =
        historyService.retrieveProjects(userId)

    // 커밋 목록
    @GetMapping("/{userId}/{projectId}")
    fun retrieveCommits(@PathVariable userId: Long, @PathVariable projectId: Long): List<Commit> =
        historyService.retrieveCommits(userId, projectId)

//    // 프론트 변경 리스트 뽑기
//    @GetMapping("/frontChanges/{commitId}")
//    fun retrieveChangePageIds(@PathVariable commitId: Int): List<Long>? =
//        historyService.retrieveChangePages(commitId)
//
//    // 백엔드 변경 리스트 뽑기
//    @GetMapping("/backendChanges/{commitId}")
//    fun retrieveChangeTableIds(@PathVariable commitId: Int): List<ChangeTable>? =
//        historyService.retrieveChangeTables(commitId)
}