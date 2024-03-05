package com.example.datahub_back.service.treeService

import com.example.datahub_back.data.treeData.commitList
import com.example.datahub_back.dto.treeDTO.*
import org.springframework.stereotype.Service

@Service
class SourceCommitService {

    // 커밋 리스트 (히스토리) 뽑아오기 - 날짜별 정렬
    fun getCommitsByBranch(branchId: Long): List<Commit> {
        val filteredCommits = commitList.filter { commit ->
            commit.branchId == branchId
        }
        return filteredCommits.sortedByDescending { it.createTime }
    }

    // 가장 최신 커밋 가져오기
    fun getLatestCommitByBranch(commitList: List<Commit>): Commit? {
        if (commitList.isEmpty()) {
            return null // 커밋 리스트가 비어있으면 null 반환
        }
        var latestCommit = commitList[0] // 초기값
        for (commit in commitList) { // 최신 커밋 찾기
            if (commit.createTime.isAfter(latestCommit.createTime)) {
                latestCommit = commit
            }
        }
        return latestCommit
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