package com.example.datahub_back.service.devTree

import com.example.datahub_back.data.devTree.commitList
import com.example.datahub_back.dto.devTree.*
import org.springframework.stereotype.Service

@Service
class CommitService {

    // 커밋 리스트 (히스토리) 뽑아오기
    fun getByBranch(branchId: Long): List<Commit> {
        return commitList.filter { commit ->
            commit.branchId == branchId }
    }

    // 백엔드 리스트 뽑아오기
    fun getTablesByCommitId(commitId: Long): MutableList<SourceTable>? {
        val commit = commitList.find { it.commitId == commitId }
        return commit?.sourceTables
    }

    // 프론트 변경 리스트 뽑기
    fun getChangePagesByCommitId(commitId: Long): MutableList<ChangePage>? {
        val commit = commitList.find { it.commitId == commitId }
        return commit?.changePages
    }

    // 백엔드 변경 리스트 뽑기
    fun getChangeTablesByCommitId(commitId: Long): MutableList<ChangeTable>? {
        val commit = commitList.find { it.commitId == commitId }
        return commit?.changeTables
    }

    // 커밋 추가
    fun createCommit(commit: Commit): Commit {
        commitList.add(commit)
        return commit
    }
}