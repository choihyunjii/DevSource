//package com.example.datahub_back.service.normalizationService
//
//import com.example.datahub_back.data.toolData.exampleColumn1
//import com.example.datahub_back.data.toolData.exampleDataBase
//import com.example.datahub_back.dto.toolDTO.Column
//import com.example.datahub_back.dto.toolDTO.Data
//import com.example.datahub_back.dto.toolDTO.Table
//import java.time.LocalDateTime
//
//class Normalization : NormalizationService{
//
//    val exampleNormalizationTableData = mutableListOf(
//        Table(2,
//            "얀예인 테이블",
//            "엔예인 정보를 모아둔 테이블",
//            1,
//            0,
//            exampleDataBase,
//            LocalDateTime.now())
//    )
//
//    val exampleNormalizationColumnData = mutableListOf(
//        Column(
//            5,
//            "회사",
//            "회사 정보를 모아둔 컬럼",
//            "Long",
//            1,
//            0,
//            0,
//            table = exampleNormalizationTableData[0],
//            joinTable = null
//        ),
//        Column(
//            6,
//            "직원번호",
//            "회사 정보를 모아둔 컬럼",
//            "Long",
//            1,
//            0,
//            0,
//            table = exampleNormalizationTableData[0],
//            joinTable = null
//        ),
//        Column(
//            7,
//            "이름",
//            "회사 정보를 모아둔 컬럼",
//            "Long",
//            1,
//            0,
//            0,
//            table = exampleNormalizationTableData[0],
//            joinTable = null
//        ),
//        Column(
//            8,
//            "SNS",
//            "회사 정보를 모아둔 컬럼",
//            "Long",
//            1,
//            0,
//            0,
//            table = exampleNormalizationTableData[0],
//            joinTable = null
//        )
//    )
//    val exampleNormalizationData = mutableListOf(
//        Data(
//            1,
//            "소속사",
//            1,
//            exampleNormalizationColumnData[0]
//        ),
//        Data(
//            2,
//            "1004",
//            1,
//            exampleNormalizationColumnData[1]
//        ),
//        Data(
//            3,
//            "아이유",
//            1,
//            exampleNormalizationColumnData[2]
//        ),
//        Data(
//            4,
//            "인스타그램  , 트위터  , 페이스북  , 유튜브  , V-Live",
//            1,
//            exampleNormalizationColumnData[3]
//        )
//    )
//    override fun findByTableID(tableID: Long): Table =
//        exampleNormalizationTableData.first { it.id == tableID }
//
//    override fun findByColumns(table: Table): List<Column> =
//        exampleNormalizationColumnData.filter {
//            it.table == table
//        }
//
//    override fun findByData(columns: List<Column>) : List<Data> {
//        val resultData : MutableList<Data> = mutableListOf()
//
//        for (column in columns){
//            val filteredData = exampleNormalizationData.filter {
//                it.column == column
//            }
//            resultData.addAll(filteredData)
//        }
//
//        return resultData
//    }
//
//    fun findByNormalizationData(data: Data) : Data? {
//        val regex = Regex(",+")
//
//        return if (regex.find(data.data) != null) {
//            data
//        } else
//            null
//
//    }
//
//    fun removeExtraSpaces(input: String): String {
//        return input.trim().replace("\\s+".toRegex(), "")
//    }
//    fun newCreateNormalizationData(newData: String){
//        val tokens = newData.split(",")
//
//        tokens.forEach{
//            exampleNormalizationData.add(
//                Data(
//                    id = (exampleNormalizationData.size + 1).toLong(),
//                    data = it,
//                    columLine = exampleNormalizationData.size + 1,
//                    exampleColumn1
//                )
//            )
//        }
//
//    }
//}
//
//fun main() {
//    val no = Normalization()
//
//    val table = no.findByTableID(2)
//
//    val columnList = no.findByColumns(table)
//
//    val data = no.findByData(columnList)
//
//    val filterData = data.mapNotNull {
//        no.findByNormalizationData(it)
//    }
//
//    val createData = no.removeExtraSpaces(filterData[0].data);
//
//    no.newCreateNormalizationData(createData)
//
//    println(no.exampleNormalizationData.map {
//        it.data + '\n'
//    })
//}