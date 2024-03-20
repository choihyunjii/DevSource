package com.example.datahub_back.service.backDataService

import com.example.datahub_back.controller.tempelate.TemplateListRequest
import com.example.datahub_back.data.toolData.exampleColumnList
import com.example.datahub_back.data.toolData.exampleDataList
import com.example.datahub_back.dto.templateDTO.ListDTO
import com.example.datahub_back.dto.templateDTO.TreeDTO
import com.example.datahub_back.dto.toolDTO.Column
import com.example.datahub_back.dto.toolDTO.Table
import org.springframework.stereotype.Service

@Service
class ColumnService {

    fun getColumnNameByTableId(tableID: Long) : List<Column> = exampleColumnList.filter {
        it.table.id == tableID
    }.map { it }.toList()

    fun getColumnByTableAndColumnName(table:Table , columnName : String)
        = exampleColumnList.filter { it.table == table && columnName == it.name }[0]

    fun getColumnById(id: Long): Column? = exampleColumnList.find { it.id == id }

    fun getDataByColumnNameAndTableID(id: Long , columnName: String) =
        exampleDataList.filter { it.column.name == columnName && it.column.table.id == id }

    fun createColumn(column: Column): Column {
        exampleColumnList.add(column)
        return column
    }
    private fun getFilterColumn(columnData : List<String> , exampleColumnData : List<Column>) : MutableList<Column>{
        val filterColumn: MutableList<Column> = mutableListOf()

        columnData.forEach { menu -> //컬럼이름과
            val filter = exampleColumnData.filter { column ->
                column.name == menu //기존 테이블을 비교하여 이름이 같다면 filter후
            }
            filterColumn.addAll(filter) //데이터에 추가
        }
        return filterColumn
    }

    //템플릿 서비스로 넘겨야될듯
    fun getTemplateListData(templateListRequest: TemplateListRequest): MutableList<ListDTO> {
        val list: MutableList<ListDTO> = mutableListOf()

        val exampleColumnData = exampleColumnList.filter { it.table.id == templateListRequest.tableID }
        //컬럼 필터링
        println(exampleColumnData)

        val columnData = templateListRequest.menuColumns
        //데이터 컬럼 리스트로 받고

        val filterColumn = getFilterColumn(columnData , exampleColumnData)

        filterColumn.forEach {column ->
            val data = exampleDataList.filter {data ->
                column == data.column
            }.map { it.data }
            list.add(ListDTO(data))
        }

        return list
    }

    fun getTemplateTreeData(templateListRequest : TemplateListRequest) : MutableList<TreeDTO>{
        val list: MutableList<TreeDTO> = mutableListOf()

        val exampleColumnData = exampleColumnList.filter { it.table.id == templateListRequest.tableID }

        val columnData = templateListRequest.menuColumns

        val filterColumn = getFilterColumn(columnData , exampleColumnData)

        var id: Long = 1
        filterColumn.forEach { column ->
            val data = exampleDataList.filter { data ->
                column == data.column
            }.map { it.data }
            list.add(TreeDTO(id, column.name, data))
            id++
        }

        return list
    }


    fun updateColumn(id: Long, updatedColumn: Column): Column? {
        val index = exampleColumnList.indexOfFirst { it.id == id }
        return if (index != -1) {
            exampleColumnList[index] = updatedColumn
            updatedColumn
        } else {
            null
        }
    }

    fun deleteColumn(id: Long): Column? {
        val column = exampleColumnList.find { it.id == id }
        if (column != null) {
            exampleColumnList.remove(column)
        }
        return column
    }
}

