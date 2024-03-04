package com.example.datahub_back.service.backDataService

import com.example.datahub_back.controller.toolController.project.ProjectRequest
import com.example.datahub_back.data.toolData.exampleProjectList
import com.example.datahub_back.dto.toolDTO.Project
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class DevProjectService {

    fun getProjectsByProfile(profileID: Long): List<Project> {
        return exampleProjectList.filter { it.profile.id == profileID }
    }

    fun getProjectsByTeamProfile(profileID: Long): List<Project> {
        return exampleProjectList.filter { project ->
            project.teamProfile.any { user -> user.id == profileID }
        }
    }

    fun getProjectById(id: Long): Project? {
        return exampleProjectList.find { it.id == id }
    }


    fun createProject(projectRequest: ProjectRequest): Project? {
        val found = exampleProjectList.find { it.name == projectRequest.name }
        //값이 같은지 확인
        val newProject = Project(
            id = 0,
            name = projectRequest.name,
            comment = projectRequest.comment,
            createTime = LocalDateTime.now(),
            updateTime = LocalDateTime.now(),
            isFavorite = 0,
            isDelete = 0,
            teamProfile = projectRequest.teamProfile,
            profile = projectRequest.profile
        )

        return if (found == null) {
            exampleProjectList.add(newProject)
            newProject
        } else null
    }


    fun updateProject(id: Long, newProject: Project): Project? {
        val index = exampleProjectList.indexOfFirst { it.id == id }
        if (index != -1) {
            exampleProjectList[index] = newProject.copy(id = id)
            return exampleProjectList[index]
        }
        return null
    }

    fun deleteProject(id: Long): Project? {
        val project = exampleProjectList.find { it.id == id }
        exampleProjectList.removeIf { it.id == id }
        return project
    }
}
