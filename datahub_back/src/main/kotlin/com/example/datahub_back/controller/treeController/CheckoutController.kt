package com.example.datahub_back.controller.treeController

import com.example.datahub_back.dto.treeDTO.Commit
import com.example.datahub_back.service.treeService.checkout.CheckoutService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin(origins = ["http://localhost:3000"])
class CheckoutController (
    private val checkoutService: CheckoutService,
) {
    @GetMapping("/{commitId}/{branchId}")
    fun checkout(@PathVariable commitId: Int, @PathVariable branchId: Long): ResponseEntity<Commit> {
        val commit = checkoutService.checkoutCommit(commitId, branchId)
        return ResponseEntity(commit, HttpStatus.OK)
    }
}