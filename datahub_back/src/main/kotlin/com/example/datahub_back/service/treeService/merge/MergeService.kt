package com.example.datahub_back.service.treeService.merge

import com.example.datahub_back.controller.treeController.merge.MergeCrashColumn
import com.example.datahub_back.controller.treeController.merge.MergeCrashData
import com.example.datahub_back.controller.treeController.merge.MergeCrashResponse
import com.example.datahub_back.dto.treeDTO.*
import com.example.datahub_back.service.treeService.BranchService
import com.example.datahub_back.service.treeService.CommitService
import com.example.datahub_back.service.treeService.SaveDataService
import com.example.datahub_back.service.treeService.commit.DataTransformer.Companion.toSourceData
import com.example.datahub_back.service.treeService.commit.TreeCommitService
import com.example.datahub_back.service.treeService.tableColumnData.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

// targetCommit에만 있는 테이블 가져와서 병합 하는 것도 추가해야 함
@Service
class MergeService (
    private val commitService: CommitService,
    private val treeCommitService: TreeCommitService,
    private val branchService: BranchService,
    private val changeTableService: ChangeTableService,
    private val changeColumnService: ChangeColumnService,
    private val changeDataService: ChangeDataService,
    private val sourceTableService: SourceTableService,
    private val sourceColumnService: SourceColumnService,
    private val sourceDataService: SourceDataService,
    private val saveDataService: SaveDataService,
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
                // 새로운 병합 커밋 만들기
                val newCommit = treeCommitService.makeNewCommit(branch.project, branch.profile,
                    "Merge : checkoutCommit[${checkCommit.commitId}] + targetCommit[${targetCommit.commitId}]")

                // 병합한(충돌 해결한) Change 데이터를 새로운 commit을 참조하는 객체로 만들어 반환
                val mergeChanges = getMergeChanges(commonTables, listOf<ChangeData>(), newCommit)

                val copySources = copySourceData(newCommit) // Source 데이터 새로운 커밋 참조로 형변환 후, 복사 해오기

                // copySources에 대해 겹치는 ChangeData는 그대로 두고, 겹치지 않는 ChangeData는 속성 바꿔서 반환
                val changeDataTypeConversion = changeDataTypeConversion(copySources, mergeChanges)

                // Source 데이터들 저장하기
                if (copySources != null) {
                    val sourcesWithoutChanges = sourcesWithoutChanges(checkCommit) // 변경 사항을 뺀 Sources
                    saveDataService.saveSourceData(sourcesWithoutChanges) // 저장하기
                    println("Source 데이터 : ${sourcesWithoutChanges}")
                }
                // Change 데이터들 저장하기
                val changes = changesDataCombination(mergeChanges, changeDataTypeConversion)
                saveDataService.saveChangesData(changes)
                // Change -> Source Data 바꾼 후 저장
                saveChangeToSourceData(changes, newCommit)
                println("Change 데이터 : ${changes}")

                return "병합 완료 (충돌 없음)"

            } else { // 있으면 충돌 객체 반환
                return crash
            }
        }
        return "오류"
    }

    private fun saveChangeToSourceData(changes: Triple<List<ChangeTable>, List<ChangeColumn>, List<ChangeData>>, newCommit: Commit) {
        val (table, column, data) = changes
        data.forEach { data ->
            sourceDataService.createData(data.toSourceData(newCommit))
        }
    }

    // source에서 change 데이터를 뺀 데이터들
    private fun sourcesWithoutChanges(checkoutCommit: Commit)
    : Triple<List<SourceTable>, List<SourceColumn>, List<SourceData>> {
        val withoutSourceTables = mutableListOf<SourceTable>()
        val withoutSourceColumns = mutableListOf<SourceColumn>()
        val withoutSourceData = mutableListOf<SourceData>()

        val sourceTables = sourceTableService.getTablesByCommit(checkoutCommit)
        val changeTables = changeTableService.getTablesByCommit(checkoutCommit)

        sourceTables.zip(changeTables).forEach { (sTable, cTable) ->
            val sColumns = sourceColumnService.getColumnsByTable(sTable)
            val sData = sourceDataService.getDataListByColumns(sColumns)

            val cColumns = changeColumnService.getColumnsByTable(cTable)
            val cData = changeDataService.getDataListByColumns(cColumns)

            // 테이블 데이터의 차이 빼내기
            val dataWithoutChanges = subtractSourceData(sData, cData)

            // 결과에 추가
            withoutSourceTables.add(sTable)
            withoutSourceColumns.addAll(sColumns)
            withoutSourceData.addAll(dataWithoutChanges)
        }

        return Triple(withoutSourceTables, withoutSourceColumns, withoutSourceData)
    }

    // SourceData 리스트와 ChangeData 리스트의 차이 찾기
    private fun subtractSourceData(sourceData: List<SourceData>, changeData: List<ChangeData>): List<SourceData> {
        val result = mutableListOf<SourceData>()
        for (data in sourceData) {
            if (!changeData.any { it.dataId == data.dataId }) {
                result.add(data)
            }
        }
        return result
    }

    private fun changesDataCombination(mergeChanges: Triple<List<ChangeTable>, List<ChangeColumn>, List<ChangeData>>?,
                                       transformationData: List<ChangeData>)
    : Triple<List<ChangeTable>, List<ChangeColumn>, List<ChangeData>>{

        var changeTables = listOf<ChangeTable>()
        var changeColumns = listOf<ChangeColumn>()

        mergeChanges?.let { (mergeTables, mergeColumns, mergeData) ->
            changeTables = mergeTables
            changeColumns = mergeColumns
        }

        return Triple(changeTables, changeColumns, transformationData)
    }


    // 병합 : 다시 데이터 꺼내서 선택하지 않은 충돌 객체만 빼서 반환
    private fun getMergeChanges(commonTables: List<Pair<ChangeTable, ChangeTable>>, crashData: List<ChangeData>, newCommit: Commit):
        Triple<List<ChangeTable>, List<ChangeColumn>, List<ChangeData>>?  {

        val changeTables = mutableListOf<ChangeTable>()
        val changeColumns = mutableListOf<ChangeColumn>()
        val changeData = mutableListOf<ChangeData>()

        commonTables.forEach { (cTable, tTable) ->
            val cColumns = changeColumnService.getColumnsByTable(cTable).sortedBy { it.columnId }
            val tColumns = changeColumnService.getColumnsByTable(tTable).sortedBy { it.columnId }

            val cChangeTable = transformChangeTableWithCommit(cTable, newCommit)
            changeTables.add(cChangeTable)
            cColumns.forEach { column ->
                val cChangeColumn = transformChangeColumnWithTable(column, cTable)
                changeColumns.add(cChangeColumn)

                val cData = changeDataService.getDataListByColumn(column).filter { it.action == 1 }
                cData.forEach { data ->
                    if (data !in crashData) {
                        changeData.add(
                            transformChangeDataWithColumn(data, column)
                        )
                    }
                } // forEach
            } // forEach

            val tChangeTable = transformChangeTableWithCommit(tTable, newCommit)
            changeTables.add(tChangeTable)
            tColumns.forEach { column ->
                val tChangeColumn = transformChangeColumnWithTable(column, tTable)
                changeColumns.add(tChangeColumn)

                val tData = changeDataService.getDataListByColumn(column).filter { it.action == 1 }

                tData.forEach { data ->
                    if (data !in crashData) {
                        changeData.add(
                            transformChangeDataWithColumn(data, column)
                        )
                    }
                } // forEach
            } // forEach
        } // forEach
        return Triple(changeTables, changeColumns, changeData)
    }

    // sourceData로부터 changeData가 중복인 데이터와 중복이 아닌 데이터 속성 바꿔서 저장
    private fun changeDataTypeConversion(copySources: Triple<List<SourceTable>, List<SourceColumn>, List<SourceData>>?,
                                         mergeChanges: Triple<List<ChangeTable>, List<ChangeColumn>, List<ChangeData>>?,
    ): List<ChangeData> {
        val newChangeData = mutableListOf<ChangeData>() // 중복은 그대로, 아닌 건 속성 바꿔 저장

        if (mergeChanges != null && copySources != null) {
            val (changeTables, changeColumns, changeData) = mergeChanges
            val (sourceTables, sourceColumns, sourceData) = copySources

            // 테이블 이름이 같은 sourceTable 찾기
            val findSourceTables = findChangeToSourceTablesName(sourceTables, changeTables)
            findSourceTables.forEach { (sTable, cTable) ->
                val sColumns = sourceColumns.filter { it.table == sTable }
                val cColumns = changeColumns.filter { it.table == cTable }
                val sData = sourceData.filter { data -> sColumns.any { column -> data.column == column }} // 테이블의 모든 데이터 뽑기
                val sDataNames = sData.map { it.data }
                val sDataLines = sData.map { it.columnLine }

                // source 데이터 안에 포함되지 않은 데이터 뽑기
                val notOverlap = changeData.filter { it.columnLine !in sDataLines && it.data !in sDataNames}

                // - 중복 데이터 저장
                val overlap = changeData.subtract(notOverlap.toSet())
                overlap.forEach{ data ->
                    newChangeData.add(data)
                }

                // - 중복이 아닌 데이터 속성 수정 후 저장
                val sourceDataLastLine = getLastColumnLine(sData) // 마지막 라인 번호 뽑기
                // line, id 재설정
                cColumns.zip(sColumns).forEach { (cColumn, sColumn) ->
                    var lastLine = sourceDataLastLine
                    val cData = notOverlap.filter { it.column == cColumn }
                    cData.forEach {
                        newChangeData.add(
                            ChangeData(
                                dataId = changeDataService.getDataMaxId() + 1, // DB 연결하면 수정하기
                                column = it.column,
                                columnLine = lastLine + 1,
                                data = it.data,
                                action = it.action
                            )
                        )
                        lastLine++
                    }
                }
            }

        }
        return newChangeData
    }

    // 마지막 라인 반환
    private fun getLastColumnLine(sPkData: List<SourceData>): Int {
        return sPkData.maxByOrNull { it.columnLine }?.columnLine?: 0
    }

    private fun copySourceData(checkCommit: Commit) :
            Triple<List<SourceTable>, List<SourceColumn>, List<SourceData>>? {
        val copySourceTables = mutableListOf<SourceTable>()
        val copySourceColumns = mutableListOf<SourceColumn>()
        val copySourceData = mutableListOf<SourceData>()

        val sourceTables = sourceTableService.getTablesByCommit(checkCommit)
        sourceTables.forEach { table ->
            // 참조 commit 교체 후 복사
            copySourceTables.add(
                transformSourceTableWithCommit(table, checkCommit)
            )
            val sourceColumns = sourceColumnService.getColumnsByTable(table)
            sourceColumns.forEach { column ->
                copySourceColumns.add(
                    transformSourceColumnWithTable(column, table)
                )
                val sourceData = sourceDataService.getDataListByColumn(column)
                sourceData.forEach { data ->
                    copySourceData.add(
                        transformSourceDataWithColumn(data, column)
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

    private fun findChangeToSourceTablesName(sourceTables: List<SourceTable>, changeTables: List<ChangeTable>)
            : List<Pair<SourceTable, ChangeTable>> {
        val commonTables = mutableListOf<Pair<SourceTable, ChangeTable>>()
        for (sourceTable in sourceTables) {
            val changeTable = changeTables.find { it.tableName == sourceTable.tableName }
            changeTable?.let { commonTables.add(Pair(sourceTable, changeTable)) }
        }
        return commonTables
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

    fun transformChangeTableWithCommit(table: ChangeTable, newCommit: Commit): ChangeTable {
        return ChangeTable (
            tableId = table.tableId,
            tableName = table.tableName,
            comment = table.comment,
            isFavorite = table.isFavorite,
            isDelete = table.isDelete,
            updateTime = LocalDateTime.now(),
            commit = newCommit
        )
    }

    fun transformChangeColumnWithTable(column: ChangeColumn, table: ChangeTable): ChangeColumn {
        return ChangeColumn(
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
    }

    fun transformChangeDataWithColumn(data: ChangeData, column: ChangeColumn): ChangeData {
        return ChangeData(
            dataId = data.dataId,
            column = column,
            columnLine = data.columnLine,
            data = data.data,
            action = data.action
        )
    }

    fun transformSourceTableWithCommit(table: SourceTable, newCommit: Commit): SourceTable {
        return SourceTable (
            tableId = table.tableId,
            tableName = table.tableName,
            comment = table.comment,
            isFavorite = table.isFavorite,
            isDelete = table.isDelete,
            updateTime = LocalDateTime.now(),
            commit = newCommit
        )
    }

    fun transformSourceColumnWithTable(column: SourceColumn, table: SourceTable): SourceColumn {
        return SourceColumn(
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
    }

    fun transformSourceDataWithColumn(data: SourceData, column: SourceColumn): SourceData {
        return SourceData(
            dataId = data.dataId,
            column = column,
            columnLine = data.columnLine,
            data = data.data
        )
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