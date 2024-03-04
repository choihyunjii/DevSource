package com.example.datahub_back.controller.treeController

import com.example.datahub_back.service.treeService.PushService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/push")
class PushController(
    private val pushService: PushService
) {
    @GetMapping("/{projectId}/{userId}")
    fun handlePush(@PathVariable projectId: Long, @PathVariable userId: String): ResponseEntity<String> {
        return try {
            val result = pushService.handlePush(projectId, userId)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: ${e.message}")
        }
    }
}
