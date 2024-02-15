package com.example.datahub_back.service

import com.example.datahub_back.data.devTree.commitList
import com.example.datahub_back.data.devTree.projectList
import com.example.datahub_back.dto.devTree.Branch
import com.example.datahub_back.dto.devTree.Commit
import org.springframework.stereotype.Service

@Service
class CommitService {

    // 커밋 리스트 (히스토리) 뽑아오기
    fun getCommitsByBranchId(branchId: Branch): List<Commit> {
        return commitList.filter { commit ->
            commit.branchId == branchId }
    }
}