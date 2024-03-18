package com.example.datahub_back.controller.treeController.History

import com.example.datahub_back.dto.toolDTO.Project
import com.example.datahub_back.dto.treeDTO.ChangeData
import com.example.datahub_back.dto.treeDTO.ChangeTable
import com.example.datahub_back.dto.treeDTO.Commit
import com.example.datahub_back.service.treeService.history.HistoryService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = ["http://localhost:3000"])
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

    // 백엔드 변경 사항 테이블 리스트 뽑기
    @GetMapping("/commitId/{commitId}")
    fun retrieveChangeTableIds(@PathVariable commitId: Int): List<ChangeTable>? =
        historyService.retrieveChangeTables(commitId)

    // 백엔드 변경 사항 행, 데이터 리스트 뽑기
    @GetMapping("/table/{tableId}")
    fun retrieveChangeColumnAndData(@PathVariable tableId: Long): MutableMap<String, List<ChangeData>> =
        historyService.retrieveChangeColumnAndData(tableId)

}