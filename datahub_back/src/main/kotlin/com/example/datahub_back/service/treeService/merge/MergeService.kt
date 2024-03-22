package com.example.datahub_back.service.treeService.merge

import com.example.datahub_back.controller.treeController.merge.MergeCrashColumn
import com.example.datahub_back.controller.treeController.merge.MergeCrashData
import com.example.datahub_back.controller.treeController.merge.MergeCrashResponse
import com.example.datahub_back.dto.treeDTO.*
import com.example.datahub_back.service.treeService.BranchService
import com.example.datahub_back.service.treeService.CommitService
import com.example.datahub_back.service.treeService.commit.DataTransformer.Companion.toSourceTable
import com.example.datahub_back.service.treeService.tableColumnData.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

// targetCommit에만 있는 테이블 가져와서 병합 하는 것도 추가해야 함
@Service
class MergeService (
    private val commitService: CommitService,
    private val branchService: BranchService,
    private val changeTableService: ChangeTableService,
    private val changeColumnService: ChangeColumnService,
    private val changeDataService: ChangeDataService,
    private val sourceTableService: SourceTableService,
    private val sourceColumnService: SourceColumnService,
    private val sourceDataService: SourceDataService,
) {
    @Transactional(rollbackFor = [RuntimeException::class])
    fun handelMerge(targetCommitId: Int, branchId: Long): Any {
        val branch = branchService.getBranchByBranchId(branchId)
        val checkCommits = branch?.let { commitService.getCommitsByBranch(it) }

        val checkCommit = checkCommits?.find { it.checkout }
        val targetCommit = commitService.getCommitByCommitId(targetCommitId)

        val checkChangeTables = checkCommit?.let { changeTableService.getTablesByCommit(it) }?.filter { it.isDelete == 0 }
        val targetChangeTables = targetCommit?.let { changeTableService.getTablesByCommit(it) }?.filter { it.isDelete == 0 }

        if (targetChangeTables != null && checkChangeTables != null) {
            val commonTables = findCommonChangeTablesName(checkChangeTables, targetChangeTables) // 테이블 이름이 같은 것을 찾아서 매핑
            val crash = crashPKCheck(commonTables)

            if (crash == null || crash.isEmpty()) { // 충돌이 없으면 병합 진행
                val mergeChanges = getMergeChanges(commonTables, listOf<ChangeData>()) // 충돌 해결한 Change 데이터
                // Source와 중복 없는 Change 가져오기
                // 그 Change의 line, id 수정
                // 새로운 커밋 객체 만들어서
                // Source로도 저장하고, Change로도 저장하기
                // 나머지 Source 복사 해온거 새로운 커밋에 저장

                return "충돌 없음"
            } else { // 있으면 충돌 객체 반환
                return crash
            }
        }
        return "오류"
    }

    // 병합 : 일단 충돌 해결한 Change 객체만 생성해서 가져오기
    private fun getMergeChanges(commonTables: List<Pair<ChangeTable, ChangeTable>>, crashData: List<ChangeData>):
        Triple<List<ChangeTable>, List<ChangeColumn>, List<ChangeData>>?  {

        val changeTables = mutableListOf<ChangeTable>()
        val changeColumns = mutableListOf<ChangeColumn>()
        val changeData = mutableListOf<ChangeData>()

        commonTables.forEach { (checkTable, targetTable) ->
            val checkColumns = changeColumnService.getColumnsByTable(checkTable).sortedBy { it.columnId }
            val targetColumns = changeColumnService.getColumnsByTable(targetTable).sortedBy { it.columnId }
            val sameColumn = areColumnsWithSameNames(checkColumns, targetColumns) // 행 길이, 이름이 같은지 확인

            if (sameColumn) { // 내용이 같은 행이라면 병합 객체 만들기
                changeTables.add(checkTable)
                checkColumns.forEach { column ->
                    changeColumns.add(column)
                    val checkData = changeDataService.getDataListByColumn(column)
                    val targetData = changeDataService.getDataListByColumn(column)

                    checkData.forEach { data ->
                        if (data !in crashData) {
                            changeData.add(data)
                        }
                    } // forEach
                    targetData.forEach { data ->
                        if (data !in crashData) {
                            changeData.add(data)
                        }
                    } // forEach
                } // forEach
            } // if
        } // forEach
        return Triple(changeTables, changeColumns, changeData)
    }

    // [이거 아닌 것 같음]
    // Change객체를 Source객체에 넣을 때 Data Id와 Line을 변경 시키는 함수 만들어야 함
    // 일치하는 테이블 이름으로 찾고, 그 테이블의 Data 중에 가장 높은 Id와 columnLine을 찾아서 저장한 다음
    // ChangeData객체의 Id, columnLine, column을 따로 수정해줌
    // 그걸 Source로 형변환해서 저장하되, 중복이면 저장하지 않음

    // change 데이터들을 수정하는 함수 (임시 id 재설정, line 지정)
    private fun changeTransformation(mergeChanges: Triple<List<ChangeTable>, List<ChangeColumn>, List<ChangeData>>?,
                                     copySources: Triple<List<SourceTable>, List<SourceColumn>, List<SourceData>>?) {
        if (mergeChanges != null && copySources != null) {
            val (changeTables, changeColumns, changeData) = mergeChanges
            val (sourceTables, sourceColumns, sourceData) = copySources

            // 테이블 이름이 같은 sourceTable 찾기
            val findSourceTables = findChangeToSourceTablesName(changeTables, sourceTables)
            findSourceTables.forEach { sTable ->
                val sColumns = sourceColumnService.getColumnsByTable(sTable)
                val sData = sourceDataService.getDataListByColumns(sColumns) // 테이블의 모든 데이터 뽑기
                val sPkData = sData.filter { it.column.isPrimaryKey == 1 } // PK만 뽑기

            }
        }
    }

    // 1. source 데이터로부터 change 데이터가 중복이 아닌 데이터 반환
    private fun notOverlapChangeDataProcess(mergeChanges: Triple<List<ChangeTable>, List<ChangeColumn>, List<ChangeData>>?,
                                            copySources: Triple<List<SourceTable>, List<SourceColumn>, List<SourceData>>?,
    ): Triple<List<ChangeTable>, List<ChangeColumn>, List<ChangeData>>? {
        var notOverlapTable = mutableListOf<ChangeTable>()
        var notOverlapColumn = mutableListOf<ChangeColumn>()
        var notOverlapData = mutableListOf<ChangeData>()

        if (mergeChanges != null && copySources != null) {
            val (changeTables, changeColumns, changeData) = mergeChanges
            val (sourceTables, sourceColumns, sourceData) = copySources

            // 테이블 이름이 같은 sourceTable 찾기
            val findSourceTables = findChangeToSourceTablesName(changeTables, sourceTables)
            findSourceTables.forEach { sTable ->
                val sColumns = sourceColumnService.getColumnsByTable(sTable)
                val sData = sourceDataService.getDataListByColumns(sColumns) // 테이블의 모든 데이터 뽑기
                val sDataNames = sData.map { it.data }
                val sDataLines = sData.map { it.columnLine }

                // source 데이터 안에 포함되지 않은 데이터 뽑기
                val notOverlap = changeData.filter { it.columnLine !in sDataLines && it.data !in sDataNames}
                notOverlap.forEach { notOverlapData.add(it) }
            }
            notOverlapTable = changeTables as MutableList<ChangeTable>
            notOverlapColumn = changeColumns as MutableList<ChangeColumn>
        }
        return Triple(notOverlapTable, notOverlapColumn, notOverlapData)
    }

    //    private fun findFirstMissingColumnLine(sPkData: List<SourceData>): Int {
//        var i = 0
//        while (true) {
//            var found = false
//            for (data in sPkData) {
//                if (data.columnLine == i) {
//                    found = true
//                    break
//                }
//            }
//            if (!found) {
//                return i
//            }
//            i++
//        }
//    }
// 꼭 라인이 1, 2, 3 이런식으로 연속적으로 있어야 하나?

    private fun copySourceData(checkCommit: Commit) :
            Triple<List<SourceTable>, List<SourceColumn>, List<SourceData>>? {
        val copySourceTables = mutableListOf<SourceTable>()
        val copySourceColumns = mutableListOf<SourceColumn>()
        val copySourceData = mutableListOf<SourceData>()

        val sourceTables = sourceTableService.getTablesByCommit(checkCommit)
        sourceTables.forEach { table ->
            // 참조 commit 교체 후 복사
            copySourceTables.add(
                SourceTable(
                    tableId = table.tableId,
                    tableName = table.tableName,
                    comment = table.comment,
                    isFavorite = table.isFavorite,
                    isDelete = table.isDelete,
                    updateTime = LocalDateTime.now(),
                    commit = checkCommit
                )
            )
            val sourceColumns = sourceColumnService.getColumnsByTable(table)
            sourceColumns.forEach { column ->
                copySourceColumns.add(
                    SourceColumn(
                        columnId = column.columnId,
                        table = table,
                        columnName = column.columnName,
                        comment = column.comment,
                        dataType = column.dataType,
                        isPrimaryKey = column.isPrimaryKey,
                        isForeignKey = column.isForeignKey,
                        isUniqueKey = column.isUniqueKey,
                        joinSourceTableId = column.joinSourceTableId
                    )
                )
                val sourceData = sourceDataService.getDataListByColumn(column)
                sourceData.forEach { data ->
                    copySourceData.add(
                        SourceData(
                            dataId = data.dataId,
                            column = column,
                            columnLine = data.columnLine,
                            data = data.data
                        )
                    )
                } // forEach
            } // forEach
        } // forEach
        return Triple(copySourceTables, copySourceColumns, copySourceData)
    }

    // 충돌 객체 반환
    private fun crashPKCheck(commonTables: List<Pair<ChangeTable, ChangeTable>>):
            List<MergeCrashResponse>? {
        val mergeCrashResponse = mutableListOf<MergeCrashResponse>()

        commonTables.forEach { (checkTable, targetTable) ->
            val checkColumns = changeColumnService.getColumnsByTable(checkTable).sortedBy { it.columnId }
            val targetColumns = changeColumnService.getColumnsByTable(targetTable).sortedBy { it.columnId }
            val sameColumn = areColumnsWithSameNames(checkColumns, targetColumns) // 행 길이, 이름이 같은지 확인

            if (sameColumn) { // 내용이 같은 행이라면 PK 충돌 확인 시작
                val checkColumnPK = changeColumnService.getPrimaryKeyInColumns(checkColumns)
                val targetColumnPK = changeColumnService.getPrimaryKeyInColumns(targetColumns)
                val checkData = checkColumnPK?.let { changeDataService.getDataListByColumn(it) }?.filter { it.action == 1 }
                val targetData = targetColumnPK?.let { changeDataService.getDataListByColumn(it) }?.filter { it.action == 1 }

                val pairPKData = findCommonPKData(checkData, targetData) // 충돌 PK 리스트
                val mergeCrashColumn = mutableListOf<MergeCrashColumn>()

                // 충돌 PK가 있다면 충돌 객체 생성 후 반환
                if (pairPKData != null) {
                    val pkLines = getPKDataLines(pairPKData) // 데이터 라인 리스트 받아놓기
                    checkColumns.zip(targetColumns).forEach { (checkColumn, targetColumn) -> // 같은 이름의 행 매핑
                        val mergeCrashData = mutableListOf<MergeCrashData>()
                        // 충돌 PK Line에 있는 데이터 모두 뽑아 저장
                        pkLines.forEach { (checkLine, targetLine) ->
                            val checkLineData = changeDataService.getDataByColumnAndLine(checkColumn, checkLine)
                            val targetLineData = changeDataService.getDataByColumnAndLine(targetColumn, targetLine)
                            if (checkLineData != null && targetLineData != null) {
                                mergeCrashData.add (
                                    MergeCrashData (
                                        check = checkLineData,
                                        target = targetLineData,
                                    )
                                )
                            } // if
                        } // forEach
                        mergeCrashColumn.add(
                            MergeCrashColumn (
                                columnName = checkColumn.columnName,
                                data = mergeCrashData
                            )
                        )
                    } // forEach
                    mergeCrashResponse.add(
                        MergeCrashResponse(
                            tableName = checkTable.tableName,
                            data = mergeCrashColumn
                        )
                    )
                } // if
            } // if
        } // forEach
        return mergeCrashResponse
    }

    private fun getPKDataLines(pairPKData: MutableList<Pair<ChangeData, ChangeData>>):
            MutableMap<Int, Int> {
        val pkDataLines = mutableMapOf<Int, Int>()
        pairPKData.forEach { (check, target) ->
            pkDataLines[check.columnLine] = target.columnLine
        }
        return pkDataLines
    }

    // PK 데이터 끼리 겹치는거 있으면 같은 data로 Pair 후 반환
    private fun findCommonPKData(checkData: List<ChangeData>?, targetData: List<ChangeData>?):
            MutableList<Pair<ChangeData, ChangeData>>? {
        if (checkData == null || targetData == null) {
            return null
        }
        val checkCrashData = checkData.filter { data -> data.data in targetData.map { it.data } }
        val targetCrashData = targetData.filter { data -> data.data in checkData.map { it.data } }

        val commonData = mutableListOf<Pair<ChangeData, ChangeData>>()
        for (check in checkCrashData) {
            val target = targetCrashData.find { it.data == check.data }
            target?.let { commonData.add(Pair(check, target)) }
        }
        if (commonData.isNotEmpty()) {
            return commonData
        }
        return null
    }

    private fun findCommonChangeTablesName(checkChangeTables: List<ChangeTable>, targetChangeTables: List<ChangeTable>)
                            : List<Pair<ChangeTable, ChangeTable>> {
        val commonTables = mutableListOf<Pair<ChangeTable, ChangeTable>>()
        for (checkTable in checkChangeTables) {
            val targetTable = targetChangeTables.find { it.tableName == checkTable.tableName }
            targetTable?.let { commonTables.add(Pair(checkTable, targetTable)) }
        }
        return commonTables
    }

    private fun findChangeToSourceTablesName(changeTables: List<ChangeTable>, sourceTables: List<SourceTable>)
            : List<SourceTable> {
        val findSourceTables = mutableListOf<SourceTable>()
        for (changeTable in changeTables) {
            val sourceTable = sourceTables.find { it.tableName == changeTable.tableName }
            sourceTable?.let { findSourceTables.add(sourceTable) }
        }
        return findSourceTables
    }


    // 행의 크기, 같은 이름을 가졌는지 확인하는 함수
    private fun areColumnsWithSameNames(checkColumns: List<ChangeColumn>, targetColumns: List<ChangeColumn>): Boolean {
        if (checkColumns.size != targetColumns.size)
            return false

        for (i in checkColumns.indices) {
            if (checkColumns[i].columnName != targetColumns[i].columnName)
                return false
        }
        return true
    }
}

// [충돌 검사하기]
// 1. 타켓 커밋 객체와 체크아웃 돼있는 커밋 객체 찾아옴
// 2. 그 커밋을 가지고 있는 change 객체들을 찾고
// ※ isDelete가 0인 것들로만 비교 시작
// 4. 이름 같은 테이블 끼리 비교 시작
// 5. 각각의 행 리스트 찾고,
// 6. 테이블의 행 길이가 같고, 이름들이 같으면
// 7. 각각의 PK들만 따로 뽑음
// 8. 그 PK끼리 겹치는 것이 있다면 (충돌)
// 9. 충돌 리스트에 저장해서 반환 (단, 모든 사이클이 다 돌고 나서) -> 충돌 해결하기

// 10. 겹치지 않는다면 (또는 충돌 해결한 후)
// 12. 새로운 commit 생성함
// 13. 테이블의 행 값이 같은 데이터만 병합해서 change 객체 만들어 저장
//     -> 충돌 해결에서 받아온 값들은 무시
// 14. 나머지 데이터는 체크아웃 데이터들 저장

// [충돌 해결 후 병합 버튼]
// 1. 선택하지 않은 ChangeData 객체를 파라미터로 모두 받아옴
// 2. 새로운 병합 commit 객체에 대한 데이터를 모두 저장하는데,
// 3. 파라미터로 받아온 값과 일치한 값은 무시함 (저장하지 않음)


// [병합 기준]
// 1. 같은 행 사이즈, 행이름을 가지고 있는 테이블 끼리만 병합한다.
// 2. 타겟 커밋에만 존재하는 테이블도 병합한다.
// 3. 나머지는 체크아웃에 있는 데이터들을 그대로 가져온다.