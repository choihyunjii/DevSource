package com.example.datahub_back.controller.toolController.table

import com.example.datahub_back.dto.toolDTO.DataBase
import com.example.datahub_back.dto.toolDTO.Table
import com.example.datahub_back.service.tableService.TableService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/table")
@CrossOrigin(origins = ["http://localhost:3000"])
class TableController  (
    @Autowired
    val tableService: TableService
) {

    @GetMapping("/{tableID}")
    fun getTablesByDatabase(@PathVariable tableID: Long): ResponseEntity<TableResponse> {
        val tableList = tableService.getTable(tableID)
        return ResponseEntity(tableList, HttpStatus.OK)
    }

//    @GetMapping
//    fun getTablesByDatabase(@RequestBody dataBase: DataBase): ResponseEntity<List<Table>> {
//        val tableList = tableService.getTablesByDatabase(dataBase)
//        return ResponseEntity(tableList, HttpStatus.OK)
//    }
//
//    @GetMapping("/projectId/{projectId}")
//    fun getTablesByProjectId(@PathVariable projectId: Long): ResponseEntity<List<Table>> {
//        val tableList = tableService.getTablesByProjectId(projectId)
//        return ResponseEntity(tableList, HttpStatus.OK)
//    }


}
