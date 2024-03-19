package com.example.datahub_back.service.treeService

import com.example.datahub_back.data.treeData.commitList
import com.example.datahub_back.dto.treeDTO.*
import org.springframework.stereotype.Service

@Service
class CommitService {

    // 커밋 리스트 뽑아오기 - 날짜별 정렬 (내림차순)
    fun getCommitsByBranch(branch: Branch): List<Commit> {
        val filteredCommits = commitList.filter { commit ->
            commit.branch == branch
        }
        return filteredCommits.sortedByDescending { it.createTime }
    }

    // 커밋 가져오기
    fun getCommitByCommitId(commitId: Int) : Commit? =
        commitList.find { it.commitId == commitId }

    // 두 번째로 최근에 만들어진 커밋 반환
    fun getSecondLatestCommitByBranch(branch: Branch): Commit? {
        val commits = getCommitsByBranch(branch)
        return commits[1]
    }

    // 커밋 추가
    fun createCommit(commit: Commit): Commit {
        commitList.add(commit)
        return commit
    }

    // 해당 브랜치의 모든 체크아웃 false
    fun uncheckCommitsForBranch(branch: Branch) {
        commitList.filter { it.branch == branch }.forEach { it.checkout = false }
    }
}