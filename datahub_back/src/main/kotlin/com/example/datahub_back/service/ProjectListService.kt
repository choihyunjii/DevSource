package com.example.datahub_back.service

import com.example.datahub_back.data.devTree.projectList
import org.springframework.stereotype.Service

@Service
class ProjectListService {

    // 프로젝트 리스트 뽑아오기
    fun getProjectNamesByUser(username: String): List<String> {
        return projectList.filter { project ->
            project.teamUsers.any {it.name == username}
            }.map { it.name }
    }
}