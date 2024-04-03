//package com.example.datahub_back.service.normalizationService
//
//import com.example.datahub_back.dto.toolDTO.Column
//import com.example.datahub_back.dto.toolDTO.Data
//import com.example.datahub_back.dto.toolDTO.Table
//
//interface NormalizationService {
//    //정규화 할시에 필요한 서비스
//    //1. ID값 으로 테이블 찾기
//    //2. 해당 컬럼 추출
//    //3. 해당 컬럼이 , / 구분자 확인하기
//    //4. 해당 컬럼의 값을 추출하여 새로운 배열로 생성
//    //5. 반환
//
//    fun findByTableID(tableID : Long) : Table
//    fun findByColumns(table : Table) : List<Column>
//
//    fun findByData(columns: List<Column>) : List<Data>
//
//}