package com.example.datahub_back.service.backDataService

import com.example.datahub_back.controller.toolController.project.ProjectRequest
import com.example.datahub_back.data.toolData.exampleDataBaseList
import com.example.datahub_back.data.toolData.exampleProjectList
import com.example.datahub_back.dto.toolDTO.DataBase
import com.example.datahub_back.dto.toolDTO.Project
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProjectService {

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
        val newDataBase = DataBase(
            id =  0,
            name = projectRequest.name,
            comment = projectRequest.dataBaseName,
            isFavorite = 0, //0이면 즐겨찾기 X
            isDelete = 0,  //0이면 삭제
            project = newProject
        )
        return if (found == null) {
            exampleProjectList.add(newProject) //프로젝트 저장
            exampleDataBaseList.add(newDataBase) // 데이터베이스 저장
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
