package com.example.datahub_back.service.treeService.commit

import com.example.datahub_back.dto.treeDTO.*
import com.example.datahub_back.service.treeService.CommitService
import com.example.datahub_back.service.treeService.SaveDataService
import com.example.datahub_back.service.treeService.tableColumnData.*
import org.springframework.stereotype.Service

@Service
class ChangeService (
    private val commitService: CommitService,
    private val sourceTableService: SourceTableService,
    private val dataTransformationService: DataTransformationService,
    private val findChangesAsTableService: FindChangesAsTableService,
    private val saveDataService: SaveDataService,
)
{
    // 변경 사항 찾아서 리스트로 반환
    fun findChanges(newCommit: Commit) : Triple<List<SourceTable>, List<SourceColumn>, List<SourceData>>? {
        val latestCommit = commitService.getSecondLatestCommitByBranch(newCommit.branch) // newCommit 이전 커밋
        val oldTables = latestCommit?.let { sourceTableService.getTablesByCommit(it) }
        // tool -> tree 형으로 바꾸기
        val toolAllData = dataTransformationService.executeChangeOperations(newCommit.branch.project, newCommit)

        toolAllData?.let { (newTables, newColumns, newData) ->
            if (oldTables != null && newTables != null) {
                // 삭제되지 않은 테이블
                val oldTablesNotDelete = sourceTableService.getTablesByIsDelete(oldTables, 0)
                val newTablesNotDelete = newTables.filter { it.isDelete == 0 }
                // 삭제된 테이블
                val oldTablesDelete = sourceTableService.getTablesByIsDelete(oldTables, 1)
                val newTablesDelete = newTables.filter { it.isDelete == 1 }

                findChangesAsTableService.findChanges(
                    oldTablesNotDelete, newTablesNotDelete, newColumns, newData, newCommit, true)
                val result = findChangesAsTableService.findChanges(
                    oldTablesDelete, newTablesDelete, newColumns, newData, newCommit, false)

                if (result != null) {
                    saveDataService.saveChangesData(result)

                    val (changeTables, changeColumns, changeData) = result
                    changeTables.forEach { data ->
                        print("${data.tableName}, ")
                    }
                    println()
                    changeColumns.forEach { data ->
                        print("${data.columnName}, ")
                    }
                    println()
                    changeData.forEach { data ->
                        print("${data.data}, ")
                    }
                }
                saveDataService.saveSourceData(toolAllData)
                findChangesAsTableService.clearChangeList()
            }
        }
        return null
    }
}

//    [변경 사항 찾고 저장하기 시작]
//    3. 커밋들을 모두 찾아서 가장 최신 커밋을 찾음
//    4. 그 커밋의 모든 테이블을 찾아옴
//    5. Tool -> tree 변환 후 데이터(테이블, 행, 값) 모두 뽑아옴
//    6. source 형으로 데이터 모두 추가
//    7. 새로 생성된 테이블 찾기에 각각의 테이블 리스트 전달