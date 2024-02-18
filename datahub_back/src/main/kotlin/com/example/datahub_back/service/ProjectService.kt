package com.example.datahub_back.service

import com.example.datahub_back.data.projectList
import com.example.datahub_back.dto.devTree.Project
import org.springframework.stereotype.Service

@Service
class ProjectService {

    // 프로젝트 리스트 뽑아오기
    fun findByUserId(userId: String): List<Project> {
        return projectList.filter { project ->
            project.teamUsers.any { user -> user.userId == userId }
        }
    }
}