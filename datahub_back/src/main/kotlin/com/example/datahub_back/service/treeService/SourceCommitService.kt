package com.example.datahub_back.service.treeService

import com.example.datahub_back.data.treeData.commitList
import com.example.datahub_back.dto.treeDTO.*
import org.springframework.stereotype.Service

@Service
class SourceCommitService {

    // 커밋 리스트 (히스토리) 뽑아오기
    fun getCommitsByBranch(branchId: Long): List<Commit> {
        return commitList.filter { commit ->
            commit.branchId == branchId }
    }

    // 백엔드 리스트 번호 뽑아오기
    fun getTableIdsByCommitId(commitId: Long): MutableList<Long>? {
        val commit = commitList.find { it.commitId == commitId }
        return commit?.sourceTableIds
    }

    // 프론트 변경 리스트 뽑기
    fun getChangePagesByCommitId(commitId: Long): MutableList<Long>? {
        val commit = commitList.find { it.commitId == commitId }
        return commit?.changePageIds
    }

    // 백엔드 변경 리스트 뽑기
    fun getChangeTablesByCommitId(commitId: Long): MutableList<Long>? {
        val commit = commitList.find { it.commitId == commitId }
        return commit?.changeTableIds
    }

    // 커밋 추가
    fun createCommit(commit: Commit): Commit {
        commitList.add(commit)
        return commit
    }
}