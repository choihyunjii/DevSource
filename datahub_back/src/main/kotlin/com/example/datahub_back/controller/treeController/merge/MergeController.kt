package com.example.datahub_back.controller.treeController.merge

import com.example.datahub_back.dto.treeDTO.Branch
import com.example.datahub_back.service.treeService.merge.MergeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/merge")
@CrossOrigin(origins = ["http://localhost:3000"])
class MergeController (
    @Autowired
    private val mergeService: MergeService
) {
    @GetMapping("/{targetCommitId}/{branchId}")
    fun handelMerge(@PathVariable targetCommitId: Int, @PathVariable branchId: Long):
            ResponseEntity<Any> {
        val merge = mergeService.handelMerge(targetCommitId, branchId)
        return ResponseEntity(merge, HttpStatus.OK)
    }

}