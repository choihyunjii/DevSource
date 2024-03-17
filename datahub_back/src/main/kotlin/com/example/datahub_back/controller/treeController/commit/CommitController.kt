package com.example.datahub_back.controller.treeController.commit

import com.example.datahub_back.service.treeService.*
import com.example.datahub_back.service.treeService.commit.TreeCommitService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/commit")
class CommitController(
    private val treeCommitService: TreeCommitService
) {
    @PostMapping("/")
    fun handleCommit(@RequestBody commitDataList: CommitRequest): ResponseEntity<String> {
        return try {
            treeCommitService.handleCommit(commitDataList)
            ResponseEntity.ok("Commit processed successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: ${e.message}")
        }
    }
}