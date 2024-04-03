package com.example.datahub_back.service.tableService

import com.example.datahub_back.controller.toolController.table.TableModifiedRequest
import com.example.datahub_back.controller.toolController.table.TableResponse
import com.example.datahub_back.controller.toolController.table.TableStatusResponse
import com.example.datahub_back.data.toolData.exampleColumnList
import com.example.datahub_back.data.toolData.exampleDataList
import com.example.datahub_back.data.toolData.exampleTableList
import com.example.datahub_back.dto.toolDTO.Column
import com.example.datahub_back.dto.toolDTO.Data
import com.example.datahub_back.dto.toolDTO.DataBase
import com.example.datahub_back.dto.toolDTO.Table
import com.example.datahub_back.dto.toolDTO.dataDTO.CreateDataDTO
import com.fasterxml.jackson.databind.JsonNode
import org.springframework.stereotype.Service

@Service
class TableService : TableRepository{
    override fun getTable(tableID: Long) : TableResponse {
        val findTable = findByTableID(tableID)
        val columns = findColumnsByTableID(findTable)

        return TableResponse(
            id = findTable.id,
            table = createTable(columns)
        )
    }
    fun getTablesByDatabase(dataBase: DataBase) : List<Table> =
        exampleTableList.filter { it.dataBase.id == dataBase.id }

    fun createTable(columns: List<Column>) : MutableMap<String, List<Data>> {
        val tableMap: MutableMap<String, List<Data>> = mutableMapOf()

        columns.forEach { column ->
            val columnData = findDataByColumn(column)
            val sortedData = sortColumnLine(columnData) //데이터 정렬 후

            tableMap[column.name] = sortedData
        }
        return tableMap
    }

    fun findByTableID(tableID: Long) : Table =
        exampleTableList.first() { it.id == tableID }

    private fun findColumnsByTableID(table: Table): List<Column> =
        exampleColumnList.filter {
            it.table.id == table.id
        }

    private fun findDataByColumn(column: Column) : List<Data> =
        exampleDataList.filter {
            it.column.id == column.id
        }

    private fun sortColumnLine(data: List<Data>): List<Data> =
        data.sortedBy { it.columLine }


    override fun findTableStatusByDatabaseID(dataBaseID: Long): TableStatusResponse =
        TableStatusResponse(
            id = dataBaseID,
            isDeleteTables = findDeleteTables(),
            isFavoriteTables = findFavoriteTables(),
            isAllTables = findAllTables()
        )

    private fun findDeleteTables() : List<Table> =
        exampleTableList.filter { it.isDelete == 1 }

    private fun findFavoriteTables() : List<Table> =
        exampleTableList.filter { it.isFavorite == 1 }

    private fun findAllTables() : List<Table> =
        exampleTableList.filter { it.isDelete != 1 }

    override fun modifiedTableAndDataFormatTest(tableModifiedRequest: TableModifiedRequest): String? {
        val groupedData = tableModifiedRequest.createData?.groupBy { it.columnLine } // 여기서 컬럼 라인으로 그룹화

        val tableID = tableModifiedRequest.tableID
        val table = findByTableID(tableID)

        val findAllByTables = exampleColumnList.filter { it.table.id == table.id }

        groupedData?.forEach { (columnLine, dataList) ->
            val nullCheckGroupData = insertionNull(dataList,findAllByTables)
            // 널값 삽입 이후
            println(nullCheckGroupData)
            val dataFormatTestResult = createDataFormTest(table, nullCheckGroupData)
            // 데이터 포멧 체크 후
            if (dataFormatTestResult == 1) {
                return null // 테스트 실패 시 즉시 종료하고 null 반환
            }
            // 모든 테스트를 통과한 경우에만 데이터 저장
            saveNewData(tableID, nullCheckGroupData)
        }
        updateData(tableModifiedRequest)


        return "데이터 저장에 성공하였습니다"
    }

    private fun insertionNull(groupList: List<CreateDataDTO>, columnList: List<Column>): List<CreateDataDTO> {
        val newGroupList: MutableList<CreateDataDTO> = mutableListOf()

        if (groupList.size == columnList.size) {
            return groupList
        } else {
            columnList.forEach { column ->
                var found = false
                groupList.forEach { groupColumn ->
                    if (column.name == groupColumn.column) {
                        found = true
                        newGroupList.add(groupColumn)
                    }
                }
                if (!found) {
                    newGroupList.add(CreateDataDTO(data = "NULL", column = column.name, columnLine = 0)) // 적절한 columnLine을 설정해야 합니다.
                }
            }
        }
        return newGroupList
    }

    //데이터 포멧
    private fun createDataFormTest(table : Table , createDataList : List<CreateDataDTO>) :Int {
        createDataList.forEach {createColumn ->
            val column = exampleColumnList.first {column ->
                column.name == createColumn.column && column.table.id == table.id
                //컬럼 리스트의 Name이 일치하고 테이블 이름이랑 일치하하는걸 찾고
            }
            checkDataType(column , createColumn)
        }
        return 0
    }
    private fun checkDataType(column: Column , createDataColumn: CreateDataDTO) : Int{
        if (column.dataType == "Int") {
            dataFormTest(createDataColumn) ?: return -1
        }
        else{
            return 0
        }
        return 0
    }

    //데이터 양식 검사
    private fun dataFormTest(createColumn: CreateDataDTO) =
            createColumn.data.toIntOrNull()


    private fun findByColumnByName(tableID : Long , columnName : String ) : Column =
        exampleColumnList.first {
            it.name == columnName && it.table.id == tableID
        }
    private fun maxColumnLine(tableID: Long , columnName: String) : Int{
        val column = findByColumnByName(tableID , columnName)
        val columns = findDataByColumn(column)
        val max = columns.maxOf {
            it.columLine
        }

        println(max)

        return max + 1
    }

    //만족시 데이터 저장
    private fun saveNewData(tableID : Long , createDataList : List<CreateDataDTO>) : String{
        createDataList.forEach {
            exampleDataList.add(
                Data(
                    id = exampleDataList.size + 1L,
                    data = it.data,
                    columLine = maxColumnLine(tableID,it.column),
                    column = findByColumnByName(tableID , it.column)
                )
            )
        }
        return "데이터 저장에 성공하였습니다."
    }
    private fun updateData(modifiedRequest: TableModifiedRequest) {
        val data = modifiedRequest.updateData
        data?.forEach { updatedData ->
            val id = updatedData.id.toInt() // id 값을 정수로 변환하거나 null을 반환
            if (id >= 0 && id < exampleDataList.size) { // 유효한 id 범위인지 확인
                exampleDataList[id - 1].data = updatedData.data //리스트는 0번 부터 시작..
                println("데이터 ID $id 업데이트 성공.")
            } else {
                println("아이디 X")
            }
        }
    }

    fun findByTableHash(tableHash : String) =
        exampleTableList.filter { it.tableHash == tableHash }[0]

    fun excelConvertTable(tableID : Long) : List<List<String>> {
        val excelData : MutableList<List<String>> = mutableListOf()
        val table = getTable(tableID)
        val keys = table.table.keys
        val columnList : MutableList<String> = mutableListOf() //컬럼 리스트를 저장할 리스트 생성

        keys.forEach {
            columnList.add(it)
        }//키 를 컬럼 리스트에 추가함

        excelData.add(columnList)
        //컬럼 값을 먼저 저장

        val values = table.table.values

        var columnIndex = 0;

        var childListSize = 0

        for (valueList in values) {
            childListSize = valueList.size
            break
        }

        for (i in 0..<childListSize){
            val dataList : MutableList<String> = mutableListOf()
            values.forEachIndexed { index, value ->
                dataList.add(value[columnIndex].data)
            }
            columnIndex++
            excelData.add(dataList)
        }
        return excelData
    }
}

