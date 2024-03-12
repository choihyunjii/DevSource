package com.example.datahub_back.service.treeService

import com.example.datahub_back.data.treeData.commitList
import com.example.datahub_back.dto.treeDTO.*
import org.springframework.stereotype.Service

@Service
class CommitService {

    // 커밋 리스트 뽑아오기 - 날짜별 정렬
    fun getCommitsByBranch(branch: Branch): List<Commit> {
        val filteredCommits = commitList.filter { commit ->
            commit.branch == branch
        }
        return filteredCommits.sortedByDescending { it.createTime }
    }

    // 커밋 가져오기
    fun getCommitByCommitId(commitId: Int) : Commit? =
        commitList.find { it.commitId == commitId }


    // 가장 최신 커밋 가져오기
    fun getLatestCommitByBranch(branch: Branch): Commit? {
        val commits = getCommitsByBranch(branch)
        if (commits.isEmpty()) {
            return null // 커밋 리스트가 비어있으면 null 반환
        }
        var latestCommit = commits[0] // 초기값
        for (commit in commits) { // 최신 커밋 찾기
            if (commit.createTime.isAfter(latestCommit.createTime)) {
                latestCommit = commit
            }
        }
        return latestCommit
    }

    // 커밋 추가
    fun createCommit(commit: Commit): Commit {
        commitList.add(commit)
        return commit
    }
}