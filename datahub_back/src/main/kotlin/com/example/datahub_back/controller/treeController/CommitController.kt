package com.example.datahub_back.controller.treeController

import com.example.datahub_back.dto.treeDTO.*
import com.example.datahub_back.service.treeService.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/commit")
class CommitController (
    private val commitService: CommitService,
    private val tableService: SourceTableService,
    private val columnService: SourceColumnService,
    private val dataService: SourceDataService,
    private val changePageService: ChangePageService,
    private val changeTableService: ChangeTableService,
    private val branchService: BranchService
) {

    @PostMapping("/")
    @Transactional(rollbackFor = [RuntimeException::class])
    fun handleCommit(@RequestBody commitData: List<CommitRequest>): ResponseEntity<String> {
        try {
            for (data in commitData) {
                // id만 뽑아서 리스트 만들기
                val sourceTableIds = data.tables.map { it.tableId }.toMutableList()
                val changeTableIds = data.changeTables.map { it.changeTableId }.toMutableList()
                val changePageIds = data.changePages.map { it.pageId }.toMutableList()

                val commit = Commit(
                    commitId = data.commitId,
                    branchId = data.branchId,
                    comment = data.comment,
                    createTime = data.createTime,
                    createUser = data.createUser,
                    sourceTableIds = sourceTableIds,
                    changeTableIds = changeTableIds,
                    changePageIds = changePageIds
                )

                commitService.createCommit(commit) // 커밋 추가
                processDataItems(data) // 나머지 추가
                branchService.updatePushByBranchId(data.branchId) // 브랜치 push 업데이트
            }
            return ResponseEntity.ok("Commit processed successfully")
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: ${e.message}")
        }
    }

    private fun processDataItems(data: CommitRequest) {
        addTables(data.tables)
        addColumns(data.columns)
        addDataItems(data.data)
        addChangePages(data.changePages)
        addChangeTables(data.changeTables)
    }

    private fun addTables(tables: List<SourceTable>) {
        for (table in tables) {
            tableService.createTable(table)
        }
    }

    private fun addColumns(columns: List<SourceColumn>) {
        for (column in columns) {
            columnService.createColumn(column)
        }
    }

    private fun addDataItems(dataItems: List<SourceData>) {
        for (dataItem in dataItems) {
            dataService.createData(dataItem)
        }
    }

    private fun addChangePages(changePages: List<ChangePage>) {
        for (changePage in changePages) {
            changePageService.createChangePage(changePage)
        }
    }

    private fun addChangeTables(changeTables: List<ChangeTable>) {
        for (changeTable in changeTables) {
            changeTableService.createChangeTable(changeTable)
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
