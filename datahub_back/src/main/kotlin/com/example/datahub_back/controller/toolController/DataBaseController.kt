package com.example.datahub_back.controller.toolController

import com.example.datahub_back.dto.toolDTO.DataBase
import com.example.datahub_back.dto.toolDTO.Project
import com.example.datahub_back.service.backDataService.DataBaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/database")
class DataBaseController (
    @Autowired
    val dataBaseService: DataBaseService
){
    // 모든 데이터베이스 가져오기
    @GetMapping
    fun getAllDataBasesByProject(@RequestBody project: Project): ResponseEntity<List<DataBase>> {
        val databaseList = dataBaseService.getAllDataBasesByProject(project)
        return ResponseEntity(databaseList, HttpStatus.OK)
    }

    // 특정 데이터베이스 가져오기
    @GetMapping("/{id}")
    fun getDataBaseById(@PathVariable id: Long): ResponseEntity<DataBase> {
        val database = dataBaseService.getDataBaseById(id)
        return ResponseEntity(database, HttpStatus.OK)
    }

    // 데이터베이스 생성
    @PostMapping
    fun createDataBase(@RequestBody database: DataBase): ResponseEntity<DataBase> {
        val createdDatabase = dataBaseService.createDataBase(database)
        return ResponseEntity(createdDatabase, HttpStatus.CREATED)
    }

    // 데이터베이스 수정
    @PutMapping("/{id}")
    fun updateDataBase(@PathVariable id: Long, @RequestBody newDatabase: DataBase): ResponseEntity<DataBase> {
        val updatedDatabase = dataBaseService.updateDataBase(id, newDatabase)
        return ResponseEntity(updatedDatabase, HttpStatus.OK)
    }

    // 데이터베이스 삭제
    @DeleteMapping("/{id}")
    fun deleteDataBase(@PathVariable id: Long): ResponseEntity<Void> {
        dataBaseService.deleteDataBase(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}