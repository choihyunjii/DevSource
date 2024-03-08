package com.example.datahub_back.controller.treeController

import com.example.datahub_back.dto.treeDTO.Branch
import com.example.datahub_back.service.backDataService.ProjectService
import com.example.datahub_back.service.treeService.BranchService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/branch")
@CrossOrigin(origins = ["http://localhost:3000"])
class BranchController (
    @Autowired
    private val branchService: BranchService,
    private val projectService: ProjectService
){
    @GetMapping("/{projectId}")
    fun getBranchByProjectId(@PathVariable projectId: Long): ResponseEntity<Branch> {
        val project = projectService.getProjectById(projectId)
        val branch = project?.let { branchService.getBranchByProject(it) }
        return ResponseEntity(branch, HttpStatus.OK)
    }

}