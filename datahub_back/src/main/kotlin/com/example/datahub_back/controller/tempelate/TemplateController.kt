package com.example.datahub_back.controller.tempelate

import com.example.datahub_back.dto.templateDTO.ListDTO
import com.example.datahub_back.dto.templateDTO.TreeDTO
import com.example.datahub_back.service.backDataService.ColumnService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/template")
@CrossOrigin(origins = ["http://localhost:3000"])
class TemplateController(
    @Autowired
    private val columnService: ColumnService
) {

    @PostMapping("/list")
    fun getListTemplateData(@RequestBody templateListRequest: TemplateListRequest) : List<ListDTO> =
        columnService.getTemplateListData(templateListRequest)

    @PostMapping("/tree")
    fun getTreeTemplateData(@RequestBody templateListRequest: TemplateListRequest) : List<TreeDTO> =
        columnService.getTemplateTreeData(templateListRequest)

}