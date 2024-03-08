//package com.example.datahub_back.service.treeService.commit
//
//import com.example.datahub_back.controller.treeController.CommitRequest
//import com.example.datahub_back.dto.treeDTO.*
//import com.example.datahub_back.service.treeService.*
//import org.springframework.stereotype.Service
//import org.springframework.transaction.annotation.Transactional
//
//@Service
//class CommitService(
//    private val sourceCommitService: SourceCommitService,
//    private val tableService: SourceTableService,
//    private val columnService: SourceColumnService,
//    private val dataService: SourceDataService,
//    private val changePageService: ChangePageService,
//    private val changeTableService: ChangeTableService,
//    private val branchService: BranchService
//) {
//    @Transactional(rollbackFor = [RuntimeException::class])
//    fun handleCommit(commitDataList: List<CommitRequest>): String {
//        for (commitData in commitDataList) {
//            try {
//                val sourceTableIds = commitData.sourceTables.map { it.tableId }
//                val changeTableIds = commitData.changeTables.map { it.changeTableId }
//                val changePageIds = commitData.changePages.map { it.pageId }
//
//                val commit = Commit(
//                    commitId = commitData.commitId,
//                    branch = commitData.branchId,
//                    comment = commitData.comment,
//                    createTime = commitData.createTime,
//                    createUser = commitData.createUser,
//                    sourceTableIds = sourceTableIds.toMutableList(),
//                    changeTables = changeTableIds.toMutableList(),
//                    changePageIds = changePageIds.toMutableList()
//                )
//                sourceCommitService.createCommit(commit)
//                processDataItems(commitData)
//                branchService.updatePushCountByBranchId(commitData.branchId)
//            } catch (e: Exception) {
//                throw RuntimeException("Error occurred during commit processing: ${e.message}")
//            }
//        }
//        return "All commits processed successfully"
//    }
//
//    private fun processDataItems(data: CommitRequest) {
//        addTables(data.sourceTables)
//        addColumns(data.columns)
//        addDataItems(data.data)
//        addChangePages(data.changePages)
//        addChangeTables(data.changeTables)
//    }
//
//    private fun addTables(sourceTables: List<SourceTable>) {
//        sourceTables.forEach { tableService.createTable(it) }
//    }
//
//    private fun addColumns(columns: List<SourceColumn>) {
//        columns.forEach { columnService.createColumn(it) }
//    }
//
//    private fun addDataItems(dataItems: List<SourceData>) {
//        dataItems.forEach { dataService.createData(it) }
//    }
//
//    private fun addChangePages(changePages: List<ChangePage>) {
//        changePages.forEach { changePageService.createChangePage(it) }
//    }
//
//    private fun addChangeTables(changeTableColumns: List<ChangeTable>) {
//        changeTableColumns.forEach { changeTableService.createChangeTable(it) }
//    }
//}
