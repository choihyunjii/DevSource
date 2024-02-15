package com.example.datahub_back.devTree.changeDetail

import com.example.datahub_back.data.devTree.changePageList
import com.example.datahub_back.dto.devTree.ChangePage
import com.example.datahub_back.dto.devTree.Commit
import org.springframework.stereotype.Service

// 프론트 수정사항
@Service
class ChangePageService {
    // 해당 커밋의 변경 리스트 뽑기
    fun getChangePagesByCommitId(commitId: Commit): List<ChangePage> {
        return changePageList.filter { changePage ->
            changePage.commitId == commitId
        }
    }
}

//// 변경 상세내역 요청
//data class FrontChangeRequest (
//    val isFrontOrBack: Int, // 0: 프론트, 1: 백엔드
//    val commit: Commit,
//    val tableOrPageName: String
//)
//
//// 선택한 커밋 변경 리스트 응답
//data class ChangeListResponse (
//    val frontList: MutableList<String>,
//    val backList: MutableList<String>,
//)
//
//
//// 백엔드 변경 상세내역 응답
//data class BackChangeResponse (
//    val backDetailList: MutableList<Any>
//)

