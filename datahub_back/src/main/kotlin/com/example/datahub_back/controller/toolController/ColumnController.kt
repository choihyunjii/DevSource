package com.example.datahub_back.controller.toolController

import com.example.datahub_back.dto.devTool.Column
import com.example.datahub_back.dto.devTool.Table
import com.example.datahub_back.service.backDataService.ColumnService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/column")
class ColumnController(
    @Autowired
    private val columnService: ColumnService
) {

    @GetMapping
    fun getColumnByTable(@RequestBody table: Table)
        = columnService.getColumnByTable(table)

    @GetMapping("/{id}")
    fun getColumnById(@PathVariable id: Long): ResponseEntity<Column> {
        val column = columnService.getColumnById(id)
        return if (column != null) {
            ResponseEntity.ok(column)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createColumn(@RequestBody column: Column): ResponseEntity<Column> {
        val createdColumn = columnService.createColumn(column)
        return ResponseEntity(createdColumn, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateColumn(@PathVariable id: Long, @RequestBody updatedColumn: Column): ResponseEntity<Column> {
        val column = columnService.updateColumn(id, updatedColumn)
        return if (column != null) {
            ResponseEntity.ok(column)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteColumn(@PathVariable id: Long): ResponseEntity<Unit> {
        val deletedColumn = columnService.deleteColumn(id)
        return if (deletedColumn != null) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
