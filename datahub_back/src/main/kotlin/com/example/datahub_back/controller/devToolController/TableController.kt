package com.example.datahub_back.controller.devToolController

import com.example.datahub_back.dto.devTool.DataBase
import com.example.datahub_back.dto.devTool.Table
import com.example.datahub_back.service.BackDataService.TableService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/table")
class TableController  (
    @Autowired
    val tableService: TableService
) {

    @GetMapping
    fun getTablesByDatabase(@RequestBody dataBase: DataBase): ResponseEntity<List<Table>> {
        val tableList = tableService.getTablesByDatabase(dataBase)
        return ResponseEntity(tableList, HttpStatus.OK)
    }

    // 특정 테이블 가져오기
    @GetMapping("/{id}")
    fun getTableById(@PathVariable id: Long): ResponseEntity<Table> {
        val table = tableService.getTableById(id)
        return ResponseEntity(table, HttpStatus.OK)
    }

    // 테이블 생성
    @PostMapping
    fun createTable(@RequestBody table: Table): ResponseEntity<Table> {
        val createdTable = tableService.createTable(table)
        return ResponseEntity(createdTable, HttpStatus.CREATED)
    }

    // 테이블 수정
    @PutMapping("/{id}")
    fun updateTable(@PathVariable id: Long, @RequestBody newTable: Table): ResponseEntity<Table> {
        val updatedTable = tableService.updateTable(id, newTable)
        return ResponseEntity(updatedTable, HttpStatus.OK)
    }

    // 테이블 삭제
    @DeleteMapping("/{id}")
    fun deleteTable(@PathVariable id: Long): ResponseEntity<Void> {
        tableService.deleteTable(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
