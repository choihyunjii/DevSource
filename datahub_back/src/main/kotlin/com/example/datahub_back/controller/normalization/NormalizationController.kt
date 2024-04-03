package com.example.datahub_back.controller.normalization


import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/normalization")
@CrossOrigin(origins = ["http://localhost:3000"])
class NormalizationController(
//    @Autowired
//    val normalizationService: NormalizationService
) {

//    @PostMapping
//    fun getNormalizationColumnData(@RequestBody requestNormalizationColumns: NormalizationRequest) : NormalizationResponse =
//        normalizationService.normalization(requestNormalizationColumns)
//            ?.toResponse()
//            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST,"해당 정규화 이루어 질 수 없습니다.") // Null일 경우
//
//    private fun List<Table>.toResponse() : NormalizationResponse =
//        NormalizationResponse(
//            this
//        )

}