package com.example.datahub_back.devTree.changeDetail

import com.example.datahub_back.dto.devTree.Commit

// 선택한 커밋 변경 리스트 요청
data class ChangeListRequest (
    val commit: Commit
)

// 변경 상세내역 요청
data class FrontChangeRequest (
    val isFrontOrBack: Int, // 0: 프론트, 1: 백엔드
    val commit: Commit,
    val tableOrPageName: String
)