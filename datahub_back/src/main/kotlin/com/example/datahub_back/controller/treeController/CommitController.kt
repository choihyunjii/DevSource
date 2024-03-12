package com.example.datahub_back.controller.treeController

import com.example.datahub_back.service.treeService.*
import com.example.datahub_back.service.treeService.commit.CommitService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/commit")
class CommitController(
//    private val commitService: CommitService,
    private val commitService: CommitService
) {
    @PostMapping("/")
    fun handleCommit(@RequestBody commitDataList: CommitRequest): ResponseEntity<String> {
        return try {
            commitService.handleCommit(commitDataList)
            ResponseEntity.ok("Commit processed successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: ${e.message}")
        }
    }
}



// 테스트용 JSON 데이터
//[
//{
//    "commitId": 1,
//    "branchId": 1,
//    "comment": "This is a test commit",
//    "createTime": "2024-02-22T15:00:00",
//    "createUser": "testUser",
//    "tables": [
//    {
//        "tableId": 1,
//        "tableName": "Table1",
//        "comment": "This is table 1",
//        "isFavorite": 1,
//        "isDelete": 0,
//        "updateTime": "2024-02-22T15:00:00"
//    },
//    {
//        "tableId": 2,
//        "tableName": "Table2",
//        "comment": "This is table 2",
//        "isFavorite": 0,
//        "isDelete": 1,
//        "updateTime": "2024-02-22T15:00:00"
//    }
//    ],
//    "columns": [
//    {
//        "tableId": 1,
//        "columnId": 1,
//        "columnName": "Column1",
//        "dataType": "String",
//        "isPrimaryKey": 1,
//        "isForeignKey": 0,
//        "isUniqueKey": 0
//    },
//    {
//        "tableId": 1,
//        "columnId": 2,
//        "columnName": "Column2",
//        "dataType": "Int",
//        "isPrimaryKey": 0,
//        "isForeignKey": 1,
//        "isUniqueKey": 0
//    }
//    ],
//    "data": [
//    {
//        "dataId": 1,
//        "columnId": 1,
//        "data": "Data1"
//    },
//    {
//        "dataId": 2,
//        "columnId": 2,
//        "data": "123"
//    }
//    ],
//    "changeTables": [
//    {
//        "changeTableId": 1,
//        "columnNumber": 1,
//        "rowNumber": 1,
//        "tableName": "Table1",
//        "action": "+",
//        "columName": "Column3",
//        "data": "New Data"
//    }
//    ],
//    "changePages": [
//    {
//        "pageId": 1,
//        "pageName": "Page1",
//        "path": "/page1"
//    }
//    ]
//}
//]
