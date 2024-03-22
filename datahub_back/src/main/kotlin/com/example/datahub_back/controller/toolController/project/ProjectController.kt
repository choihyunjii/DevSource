package com.example.datahub_back.controller.toolController.project
import com.example.datahub_back.dto.toolDTO.Project
import com.example.datahub_back.service.backDataService.ProjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/project")
@CrossOrigin(origins = ["http://localhost:3000"])
class ProjectController(
    @Autowired
    val projectService: ProjectService
) {
    @GetMapping("/profile/{profileID}")
    fun getProjectsByProfile(@PathVariable profileID: Long): ResponseEntity<List<Project>> {
        val projectList = projectService.getProjectsByProfile(profileID)
        return ResponseEntity(projectList, HttpStatus.OK)
    }

    // 특정 프로젝트 가져오기
    @GetMapping("/{id}")
    fun getProjectById(@PathVariable id: Long): ResponseEntity<Project> {
        val project = projectService.getProjectById(id)
        return ResponseEntity(project, HttpStatus.OK)
    }

    // 프로젝트 생성
    @PostMapping
    fun createProject(@RequestBody projectRequest: ProjectRequest): ProjectResponse =
        projectService.createProject(projectRequest)
            ?. toResponse(dataBaseName = projectRequest.dataBaseName) //Null이 아닐경우
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST,"프로젝트 이름이 이미 존재합니다.") // Null일 경우

    // 프로젝트 수정
    @PutMapping("/{id}")
    fun updateProject(@PathVariable id: Long, @RequestBody newProject: Project): ResponseEntity<Project> {
        val updatedProject = projectService.updateProject(id, newProject)
        return ResponseEntity(updatedProject, HttpStatus.OK)
    }

    // 프로젝트 삭제
    @DeleteMapping("/{id}")
    fun deleteProject(@PathVariable id: Long): ResponseEntity<Void> {
        projectService.deleteProject(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    private fun Project.toResponse(dataBaseName : String) : ProjectResponse =
        ProjectResponse(
            projectName = this.name,
            comment = this.comment,
            createTime = this.createTime,
            profile = this.createProfile,
            teamProfile = this.teamProfile,
            dataBaseName = dataBaseName
        )
}