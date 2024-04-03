package com.example.datahub_back.controller.treeController.commit

data class CommitRequest(
    val projectId: Long,
    val profileId: Long,
    val comment: String,
)

//{
//    "projectId": "1",
//    "profileId": "1",
//    "comment": "커밋 테스트"
//}