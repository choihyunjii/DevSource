package com.example.datahub_back.service.treeService.commit

import com.example.datahub_back.dto.toolDTO.Project
import com.example.datahub_back.dto.treeDTO.*
import com.example.datahub_back.service.treeService.CommitService
import com.example.datahub_back.service.treeService.SourceTableService
import org.springframework.stereotype.Service

// 최신 커밋의 테이블 리스트와 Tool -> Tree 데이터로 바꾼 데이터 리스트를 변경사항 찾기 함수에 넘겨주는 클래스
@Service
class AddChangeService (
    private val commitService: CommitService,
    private val sourceTableService: SourceTableService,
    private val dataTransformationService: DataTransformationService,
    private val addChangesAsTableService: AddChangesAsTableService,
)
{
//    [변경 사항 찾고 저장하기 시작]
//    3. 커밋들을 모두 찾아서 가장 최신 커밋을 찾음
//    4. 모든 테이블을 찾아옴
//    5. 프로젝트 객체로 Tool 데이터(테이블, 행, 값) 모두 뽑아옴
//    6. 새로 생성된 테이블 찾기에 각각의 테이블 리스트 전달

//    [변경 사항 찾고 저장하기 끝난 후]
//    1. Commit 객체로 Tool 테이블의 데이터 자체들을 모두 SourceTable, SourceColumn, SourceData로 저장
    fun addChanges(branch: Branch, newCommit: Commit, project: Project) {
        val latestCommit = commitService.getLatestCommitByBranch(branch) // 최신커밋
        val oldTables = latestCommit?.let { sourceTableService.getTablesByCommit(it) }

        val toolAllData = dataTransformationService.executeChangeOperations(project, newCommit)

        addChangesAsTableService.addChangesAsTable(oldTables, toolAllData)
    }
}