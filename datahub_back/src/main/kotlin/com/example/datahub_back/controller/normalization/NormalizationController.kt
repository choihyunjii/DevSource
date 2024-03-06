package com.example.datahub_back.controller.normalization

import com.example.datahub_back.controller.toolController.project.ProjectResponse
import com.example.datahub_back.dto.toolDTO.Project
import com.example.datahub_back.dto.toolDTO.Table
import com.example.datahub_back.service.normalizationService.NormalizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/normalization")
@CrossOrigin(origins = ["http://localhost:3000"])
class NormalizationController(
    @Autowired
    val normalizationService: NormalizationService
) {

    @PostMapping
    fun getNormalizationColumnData(@RequestBody requestNormalizationColumns: NormalizationRequest) : List<Table> =
        normalizationService.normalization(requestNormalizationColumns)
            ?.toMutableList()//처리된 테이블을 가공해서 전송 일단 이렇게 넣어두기!
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST,"해당 정규화 이루어 질 수 없습니다.") // Null일 경우


}