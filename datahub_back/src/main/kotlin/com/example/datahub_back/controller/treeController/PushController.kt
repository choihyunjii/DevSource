package com.example.datahub_back.controller.treeController

import com.example.datahub_back.dto.toolDTO.Profile
import com.example.datahub_back.dto.toolDTO.Project
import com.example.datahub_back.service.treeService.pullRequest.PullRequestService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/push")
class PushController(
    private val pullRequestService: PullRequestService
) {
    @GetMapping("/{project}/{user}")
    fun handlePush(@PathVariable project: Project, @PathVariable user: Profile): ResponseEntity<String> {
        return try {
            val result = pullRequestService.handlePush(project, user)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: ${e.message}")
        }
    }
}
