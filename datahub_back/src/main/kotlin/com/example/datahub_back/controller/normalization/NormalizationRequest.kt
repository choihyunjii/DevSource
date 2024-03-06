package com.example.datahub_back.controller.normalization

data class NormalizationRequest(
    val normalizationStep : Int, //1정규화 2정규화 3정규화의 순서를 담을 변수
    val normalizationColumns : MutableList<String>,
    val normalizationTableID : Long
)