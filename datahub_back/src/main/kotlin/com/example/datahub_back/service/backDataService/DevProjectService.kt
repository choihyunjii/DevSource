package com.example.datahub_back.service.backDataService

import com.example.datahub_back.data.toolData.exampleProjectList
import com.example.datahub_back.dto.toolDTO.Project
import org.springframework.stereotype.Service

@Service
class DevProjectService {

    fun getProjectsByProfile(profileID: Long): List<Project> {
        return exampleProjectList.filter { it.profile.id == profileID }
    }

    fun getProjectById(id: Long): Project? {
        return exampleProjectList.find { it.id == id }
    }

    fun createProject(project: Project): Project {
        project.id = (exampleProjectList.maxByOrNull { it.id }?.id ?: 0) + 1
        exampleProjectList.add(project)
        return project
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
