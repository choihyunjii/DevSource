package com.example.datahub_back.service.treeService.commit

import com.example.datahub_back.controller.treeController.CommitRequest
import com.example.datahub_back.dto.treeDTO.Commit
import com.example.datahub_back.service.treeService.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class TreeCommitService(
    private val commitService: CommitService,
    private val branchService: BranchService,
    private val addChangeService: AddChangeService,
) {
//    [비교 로직 (변경 사항 찾기) 시작 전]
//    1. 해당 Project 객체를 가지고 있는 Branch를 찾음
//    2. 새로운 Commit 객체 생성 후
//    3. Branch와 Commit을 변경 사항 찾기 로직에 전달
    @Transactional(rollbackFor = [RuntimeException::class])
    fun handleCommit(commitData: CommitRequest): String {
        try {
            val project = commitData.project
            val branch = branchService.getBranchByProject(project)

            // 해시코드 만들기
            val commitHashCode = "${project}${LocalDateTime.now()}".hashCode()
            if (branch != null) {
                val commit = Commit(
                    commitId = commitHashCode,
                    branch = branch,
                    comment = commitData.comment,
                    createTime = LocalDateTime.now(),
                    createUser = commitData.profile.username,
                )
                // 커밋 추가
                commitService.createCommit(commit)
                addChangeService.addChanges(branch, commit, project)
                // 해당 브랜치의 push 업데이트
                branchService.updatePushCountByBranchId(branch.branchId)
            }
        } catch (e: Exception) {
            throw RuntimeException("Error occurred during commit processing: ${e.message}")
        }
        return "All commits processed successfully"
    }
}
