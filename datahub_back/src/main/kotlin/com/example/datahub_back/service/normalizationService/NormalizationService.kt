package com.example.datahub_back.service.normalizationService

import com.example.datahub_back.controller.normalization.NormalizationRequest
import com.example.datahub_back.dto.toolDTO.Table
import org.springframework.stereotype.Service

@Service
class NormalizationService {

    private fun normalizationStepOne(normalizationColumns: MutableList<String>): List<Table>?{
        return null
    }
    private fun normalizationStepTwo(normalizationColumns: MutableList<String>) : List<Table>?{
        return null
    }
    private fun normalizationStepThee(normalizationColumns: MutableList<String>) : List<Table>?{
        return null
    }

    fun normalization(normalizationRequest: NormalizationRequest) : List<Table>?{
        when(normalizationRequest.normalizationStep){
            1 -> normalizationStepOne(normalizationRequest.normalizationColumns)
            2 -> normalizationStepTwo(normalizationRequest.normalizationColumns)
            3 -> normalizationStepThee(normalizationRequest.normalizationColumns)
        }
        return null
    }
}