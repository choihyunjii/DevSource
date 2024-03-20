package com.example.datahub_back.service.treeService.merge

import com.example.datahub_back.controller.treeController.merge.MergeCrashData
import com.example.datahub_back.controller.treeController.merge.MergeCrashResponse
import com.example.datahub_back.dto.treeDTO.ChangeColumn
import com.example.datahub_back.dto.treeDTO.ChangeData
import com.example.datahub_back.dto.treeDTO.ChangeTable
import com.example.datahub_back.service.treeService.BranchService
import com.example.datahub_back.service.treeService.CommitService
import com.example.datahub_back.service.treeService.tableColumnData.ChangeColumnService
import com.example.datahub_back.service.treeService.tableColumnData.ChangeDataService
import com.example.datahub_back.service.treeService.tableColumnData.ChangeTableService
import org.springframework.stereotype.Service

@Service
class MergeService (
    private val commitService: CommitService,
    private val branchService: BranchService,
    private val changeTableService: ChangeTableService,
    private val changeColumnService: ChangeColumnService,
    private val changeDataService: ChangeDataService,
) {
    fun handelMerge(targetCommitId: Int, branchId: Long): Any {
        val branch = branchService.getBranchByBranchId(branchId)
        val checkCommits = branch?.let { commitService.getCommitsByBranch(it) }

        val checkCommit = checkCommits?.find { it.checkout }
        val targetCommit = commitService.getCommitByCommitId(targetCommitId)

        val checkChangeTables = checkCommit?.let { changeTableService.getTablesByCommit(it) }?.filter { it.isDelete == 0 }
        val targetChangeTables = targetCommit?.let { changeTableService.getTablesByCommit(it) }?.filter { it.isDelete == 0 }

        if (targetChangeTables != null && checkChangeTables != null) {
            val crash = crashCheck(checkChangeTables, targetChangeTables)

            if (crash == null) { // 충돌이 없으면 병합 진행

            } else { // 있으면 충돌 객체 반환
                return crash
            }
        }
        return "오류"
    }

    // 충돌 검사
    private fun crashCheck(checkChangeTables: List<ChangeTable>, targetChangeTables: List<ChangeTable>):
            List<MergeCrashResponse>? {
        val mergeCrashResponse = mutableListOf<MergeCrashResponse>()
        val commonTables = findCommonTables(checkChangeTables, targetChangeTables) // 테이블 이름이 같은 것을 찾아서 매핑

        commonTables.forEach { (checkTable, targetTable) ->
            val checkColumns = changeColumnService.getColumnsByTable(checkTable).sortedBy { it.columnId }
            val targetColumns = changeColumnService.getColumnsByTable(targetTable).sortedBy { it.columnId }
            val sameColumn = areColumnsWithSameNames(checkColumns, targetColumns)

            if (sameColumn) { // 내용이 같은 행이라면 PK 충돌 확인 시작
                val checkColumnPK = changeColumnService.getPrimaryKeyInColumns(checkColumns)
                val targetColumnPK = changeColumnService.getPrimaryKeyInColumns(targetColumns)
                val checkData = checkColumnPK?.let { changeDataService.getDataListByColumn(it) }?.filter { it.action == 1 }
                val targetData = targetColumnPK?.let { changeDataService.getDataListByColumn(it) }?.filter { it.action == 1 }

                val commonPKData = findCommonData(checkData, targetData)

                if (commonPKData != null) { // 충돌 PK 데이터가 있다면, 해당 PK와 같은 line에 있는 데이터 찾아서 저장
                    val mergeCrashData = mutableListOf<MergeCrashData>()

                    commonPKData?.let { (checkCrashData, targetCrashData) ->
                        val checkData = mutableListOf<ChangeData>()
                        val targetData = mutableListOf<ChangeData>()
                        checkCrashData.forEach { crash ->
                            val lineData = changeDataService.findDataListByColumnAndLine(checkColumns, crash.columnLine)?.sortedBy { it.dataId } // 해당 라인의 데이터 목록 가져오기
                            lineData?.forEach { data ->
                                checkData.add(data) // 한 줄 데이터 저장
                            }
                        }
                        targetCrashData.forEach { crash ->
                            val lineData = changeDataService.findDataListByColumnAndLine(targetColumns, crash.columnLine)?.sortedBy { it.dataId }
                            lineData?.forEach { data ->
                                targetData.add(data)
                            }
                        }
                        mergeCrashData.add(MergeCrashData ( // 한 줄 데이터 짝 지어서 저장
                            target = targetData,
                            check = checkData
                        ))
                    } // let
                    mergeCrashResponse.add(MergeCrashResponse (
                        tableName = checkTable.tableName,
                        columns = checkColumns as MutableList<ChangeColumn>,
                        data = mergeCrashData
                    ))
                } // if
            } // if
        } // forEach
        return mergeCrashResponse
    }

    // PK 데이터 끼리 겹치는거 있으면 반환
    private fun findCommonData(checkData: List<ChangeData>?, targetData: List<ChangeData>?):
            Pair<List<ChangeData>, List<ChangeData>>? {
        if (checkData == null || targetData == null) {
            return null
        }
        val checkCrashData = checkData.filter { data -> data.data in targetData.map { it.data } }
        val targetCrashData = targetData.filter { data -> data.data in checkData.map { it.data } }

        return Pair(checkCrashData, targetCrashData)
    }



    private fun findCommonTables(checkChangeTables: List<ChangeTable>, targetChangeTables: List<ChangeTable>)
                            : List<Pair<ChangeTable, ChangeTable>> {
        val commonTables = mutableListOf<Pair<ChangeTable, ChangeTable>>()
        for (checkTable in checkChangeTables) {
            val targetTable = targetChangeTables.find { it.tableName == checkTable.tableName }
            targetTable?.let { commonTables.add(Pair(checkTable, targetTable)) }
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
// 10. 겹치지 않는다면(또는 충돌 해결한 후) change 객체 리스트에 두 커밋의 변경 사항을 모두 저장
// 11. 그것을 임시로 저장해두고 (반환 받고)
// 12. 새로운 commit 생성함
// 13. change형으로 병합한 변경사항 저장하고,
// 14. 체크아웃 커밋의 source 객체들을 모두 복사해서 저장하되,
// 15. 타겟 커밋의 변경 사항인 change를 source객체로 변경해서 위의 source 객체들과 함께 저장

// [충돌 해결 하기]
// 1. 반환 받은 충돌 리스트를 사용자에게 보여줘서 어떤 행을 사용할지 정보를 받아낸다
// 2. 그 받은 정보를 다시 충돌 검사 로직에 반환 시켜서 chage 객체 리스트로 저장한다.
// [충돌 해결 후 병합 버튼]
// 1. 그대로 파라미터를 넘겨 받되, 선택한 행에 대한 정보들을 모두 받음
// 2. 충돌 검사 로직대로 충돌 확인 후 충돌이 나면 그건 무시 (저장하지 않음)
// 3. 모든 데이터를 저장했다면 선택한 행 정보도 같이 저장
// 4. 그 뒤로 같음