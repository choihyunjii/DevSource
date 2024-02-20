package com.example.datahub_back.service.devSource

import com.example.datahub_back.data.devTree.commitList
import com.example.datahub_back.dto.devTree.*
import org.springframework.stereotype.Service

@Service
class CommitService {

    // 커밋 리스트 (히스토리) 뽑아오기
    fun findByBranch(branchId: Int): List<Commit> {
        return commitList.filter { commit ->
            commit.branchId == branchId }
    }

    // 백엔드 리스트 뽑아오기
    fun findTablesByCommitId(commitId: Int): MutableList<Table>? {
        val commit = commitList.find { it.commitId == commitId }
        return commit?.tables
    }

    // 프론트 리스트 뽑아오기
    fun findPagesByCommitId(commitId: Int): MutableList<Page>? {
        val commit = commitList.find { it.commitId == commitId }
        return commit?.pages
    }

    // 프론트 변경 리스트 뽑기
    fun findChangePagesByCommitId(commitId: Int): MutableList<ChangePage>? {
        val commit = commitList.find { it.commitId == commitId }
        return commit?.changePages
    }

    // 백엔드 변경 리스트 뽑기
    fun findChangeTablesByCommitId(commitId: Int): MutableList<ChangeTable>? {
        val commit = commitList.find { it.commitId == commitId }
        return commit?.changeTables
    }
}