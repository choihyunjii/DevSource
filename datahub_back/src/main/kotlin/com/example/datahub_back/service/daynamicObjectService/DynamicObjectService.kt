package com.example.datahub_back.service.daynamicObjectService

import com.example.datahub_back.data.toolData.exampleDataList
import com.example.datahub_back.dto.toolDTO.Data
import com.example.datahub_back.service.backDataService.ColumnService
import com.example.datahub_back.service.tableService.TableService
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.text.StringBuilder


@Service
class DynamicObjectService(
    @Autowired
    private val tableService: TableService,
    private val columnService: ColumnService,

) {
    fun createDynamicObject(tableHash :String , node: JsonNode) : String{

        val table = tableService.findByTableHash(tableHash)
        val columns = columnService.getColumnNameByTableId(table.id)

        columns.forEach {column ->
            if (node.has(column.name)){
                exampleDataList.add(
                    Data(
                        id = exampleDataList.size + 1L,
                        data = node.get(column.name).asText(),
                        column = column,
                        columLine = exampleDataList.size + 1
                    )
                )
            }
            else{
                return "해당 객체타입과 데이터베이스 타입이 일치하지 않습니다."
            }
        }
        return "성공적으로 객체가 저장되었습니다."
    }

    fun getDynamicObject(objectName: String, id: Long){

    }

    fun updateDynamicObject(objectName: String, id: Long,updateData: JsonNode){

    }

    fun deleteDynamicObject(objectName: String, id: Long){

    }

    fun getTableHash(tableID : Long) =
        tableService.findByTableID(tableID).tableHash

    fun tableDataConvertJsonNode(tableHash: String) : List<JsonNode>{
        val table = tableService.findByTableHash(tableHash)
        val getTable = tableService.getTable(table.id)

        return parseJSON(getTable.table)
    }
    fun parseJSON(getTable: Map<String, List<Data>>) : List<JsonNode>{
        val parseJsonList : MutableList<JsonNode> = mutableListOf()
        val objectMapper = ObjectMapper()
        val values = getTable.values
        var columnIndex = 0;

        var childListSize = 0

        for (valueList in values) {
            childListSize = valueList.size
            break
        }

        for (i in 0..<childListSize){
            val sb : StringBuilder = StringBuilder()
            sb.append(" {\n")
            values.forEachIndexed { index, value ->
                sb.append(" \"${value[columnIndex].column.name}\" : \"${value[columnIndex].data}\"")
                if (index != values.size - 1) {
                    sb.append(",\n") // values의 마지막 값이 아니면 쉼표 출력
                }else
                    sb.append("\n")
            }
            sb.append(" }")
            columnIndex++

            val jsonNode: JsonNode = objectMapper.readTree(sb.toString())
            parseJsonList.add(jsonNode)
        }

        return parseJsonList
    }


}

