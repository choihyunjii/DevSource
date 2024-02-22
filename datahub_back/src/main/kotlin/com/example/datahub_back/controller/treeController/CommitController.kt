package com.example.datahub_back.controller.treeController

import com.example.datahub_back.dto.devTool.Column
import com.example.datahub_back.dto.devTool.Data
import com.example.datahub_back.dto.devTool.Table
import com.example.datahub_back.dto.devTree.ChangePage
import com.example.datahub_back.dto.devTree.ChangeTable
import com.example.datahub_back.dto.devTree.Page
import com.example.datahub_back.service.backDataService.ColumnService
import com.example.datahub_back.service.backDataService.DataService
import com.example.datahub_back.service.backDataService.TableService
import com.example.datahub_back.service.devTree.*
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/commit")
class CommitController (
    private val commitService: CommitService,
    private val tableService: TableService,
    private val columnService: ColumnService,
    private val dataService: DataService,
    private val pageService: PageService,
    private val changePageService: ChangePageService,
    private val changeTableService: ChangeTableService,
    private val branchService: BranchService
) {

    // Table, Column, Data DTO 다 동호가 만든거 썼더니 내가 만든 DTO랑 충돌나서 한 줄에 오류 뜨는데
    // 내일 테이블 통일 시키고 나면 오류 안 뜰거야 아마도
//    @PostMapping("/")
//    @Transactional(rollbackFor = [RuntimeException::class])
//    fun handleCommit(@RequestBody commitData: List<CommitRequest>): ResponseEntity<String> {
//        try {
//            for (data in commitData) {
//                val commit = Commit(
//                    commitId = data.commitId,
//                    branchId = data.branchId,
//                    comment = data.comment,
//                    createTime = data.createTime,
//                    createUser = data.createUser,
//                    tables = data.tables.toMutableList(),
//                    pages = data.pages.toMutableList(),
//                    changeTables = data.changeTables.toMutableList(),
//                    changePages = data.changePages.toMutableList()
//                )
//                commitService.createCommit(commit) // 커밋 추가
//                processDataItems(data) // 나머지 추가
//                branchService.updatePushByBranchId(data.branchId) // 브랜치 push 업데이트
//            }
//            return ResponseEntity.ok("Commit processed successfully")
//        } catch (e: Exception) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: ${e.message}")
//        }
//    }

    private fun processDataItems(data: CommitRequest) {
        addTables(data.tables)
        addColumns(data.columns)
        addDataItems(data.data)
        addPages(data.pages)
        addChangePages(data.changePages)
        addChangeTables(data.changeTables)
    }

    private fun addTables(tables: List<Table>) {
        for (table in tables) {
            tableService.createTable(table)
        }
    }

    private fun addColumns(columns: List<Column>) {
        for (column in columns) {
            columnService.createColumn(column)
        }
    }

    private fun addDataItems(dataItems: List<Data>) {
        for (dataItem in dataItems) {
            dataService.createData(dataItem)
        }
    }

    private fun addPages(pages: List<Page>) {
        for (page in pages) {
            pageService.createPage(page)
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