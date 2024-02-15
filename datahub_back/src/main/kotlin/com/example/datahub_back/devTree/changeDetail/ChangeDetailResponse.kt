package com.example.datahub_back.devTree.changeDetail

// 선택한 커밋 변경 리스트 응답
data class ChangeListResponse (
    val frontList: MutableList<String>,
    val backList: MutableList<String>,
)

// 프론트 변경 상세내역 응답
data class FrontChangeResponse (
    val path: String
)

// 백엔드 변경 상세내역 응답
data class BackChangeResponse (
    val backDetailList: MutableList<Any>
)