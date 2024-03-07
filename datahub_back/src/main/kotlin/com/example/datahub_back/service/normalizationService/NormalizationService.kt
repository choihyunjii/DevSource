package com.example.datahub_back.service.normalizationService

import com.example.datahub_back.controller.normalization.NormalizationRequest
import com.example.datahub_back.data.toolData.exampleTable
import com.example.datahub_back.dto.toolDTO.Column
import com.example.datahub_back.dto.toolDTO.Table
import com.example.datahub_back.service.backDataService.ColumnService
import com.example.datahub_back.service.backDataService.DataService
import com.example.datahub_back.service.backDataService.TableService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NormalizationService(
    @Autowired
    private val tableService: TableService,
    private val columnService: ColumnService,
    private val dataService: DataService
) {

    fun normalizationStepOne(table: Table, columnName: String) {
        val normalizeColumnList : MutableList<Column> = mutableListOf()

        val column = columnService.getColumnByTableAndColumnName(table,columnName)

        val data = dataService.getDataByColumn(column)

        val dataValueToString = data.data
        val snsPlatforms = dataValueToString.split("/") // "/"를 기준으로 각 소셜 미디어 플랫폼을 분할

        // 각 소셜 미디어 플랫폼을 새로운 열로 추가
        snsPlatforms.forEachIndexed { index, platform ->
            val newColumnName = "${column.name}_${index + 1}" // 새로운 열의 이름 생성

            val newColumn = Column(
                id = 0,
                name = newColumnName,
                comment = "Generated from $columnName",
                dataType = "String", // 데이터 유형 설정
                isPrimaryKey = 0,
                isForeignKey = 0,
                isUniqueKey = 0,
                table = column.table,
                joinTable = null
            )

            normalizeColumnList.add(newColumn)
        }
    }

    private fun normalizationStepTwo(normalizationColumns: MutableList<String>,table: Table) : List<Table>?{
        return null
    }
    private fun normalizationStepThee(normalizationColumns: MutableList<String>,table: Table) : List<Table>?{
        return null
    }
    private fun findByTable(tableID : Long) : Table
        = exampleTable

    fun normalization(normalizationRequest: NormalizationRequest) : List<Table>?{
        val table = findByTable(normalizationRequest.normalizationTableID)

        when(normalizationRequest.normalizationStep){
            1 -> normalizationStepOne(table,normalizationRequest.normalizationColumns[0])

            2 -> normalizationStepTwo(normalizationRequest.normalizationColumns, table)

            3 -> normalizationStepThee(normalizationRequest.normalizationColumns,table)
        }

        return null
    }
}