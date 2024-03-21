package com.example.datahub_back.controller.toolController.table

import com.example.datahub_back.service.tableService.TableService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/table")
@CrossOrigin(origins = ["http://localhost:3000"])
class TableController  (
    @Autowired
    val tableService: TableService
) {

    @GetMapping("/{tableID}")
    fun getTablesByDatabase(@PathVariable tableID: Long): ResponseEntity<TableResponse> =
        ResponseEntity(tableService.getTable(tableID), HttpStatus.OK)


    @GetMapping("/status/{dataBaseID}")
    fun getTableStatusByDataBase(@PathVariable dataBaseID : Long) : ResponseEntity<TableStatusResponse> =
        ResponseEntity(tableService.findTableStatusByDatabaseID(dataBaseID),HttpStatus.OK)

    @GetMapping("/excel/{tableID}")
    fun excelConvertTable(@PathVariable tableID: Long) =
        tableService.excelConvertTable(tableID)



    @PostMapping("/modifiedTable")
    fun getModifiedTable(@RequestBody modifiedTable: TableModifiedRequest): ResponseEntity<String> =
        tableService.modifiedTableAndDataFormatTest(modifiedTable)
            ?.let { ResponseEntity.ok("데이터값이 저장되었습니다.") }
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "데이터값 일치하지 않습니다.")



}
