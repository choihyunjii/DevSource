package com.example.datahub_back.service.treeService

import com.example.datahub_back.data.treeData.projectList
import com.example.datahub_back.dto.treeDTO.SourceProject
import org.springframework.stereotype.Service

@Service
class ProjectService {

    // 프로젝트 리스트 뽑아오기
    fun getByUserId(userId: String): List<SourceProject> {
        return projectList.filter { project ->
            project.teamSourceUsers.any { user -> user.userId == userId }
        }
    }
}
