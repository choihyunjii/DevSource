package com.example.datahub_back.controller.toolController.data

import com.example.datahub_back.dto.toolDTO.Data
import com.example.datahub_back.service.backDataService.DataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/data")
@CrossOrigin(origins = ["http://localhost:3000"])

class DataController(
    @Autowired
     val dataService: DataService
) {
    @GetMapping
    fun getDataByColumn(): ResponseEntity<List<Data>> {
        val dataList = dataService.getAllData()
        return ResponseEntity(dataList, HttpStatus.OK)
    }

    @GetMapping("/{columnID}")
    fun getDataByColumn(@PathVariable columnID : Long) =
        dataService.getDataByColumn(columnID)

//    @GetMapping("/{id}")
//    fun getDataById(@PathVariable id: Long): ResponseEntity<Data> {
//        val data = dataService.getDataById(id)
//        return if (data != null) {
//            ResponseEntity(data, HttpStatus.OK)
//        } else {
//            ResponseEntity(HttpStatus.NOT_FOUND)
//        }
//    }

    @PostMapping
    fun createData(@RequestBody data: Data): ResponseEntity<Data> {
        val createdData = dataService.createData(data)
        return ResponseEntity(createdData, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateData(@PathVariable id: Long, @RequestBody newData: Data): ResponseEntity<Data> {
        val updatedData = dataService.updateData(id, newData)
        return if (updatedData != null) {
            ResponseEntity(updatedData, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteData(@PathVariable id: Long): ResponseEntity<Data> {
        val deletedData = dataService.deleteData(id)
        return if (deletedData != null) {
            ResponseEntity(deletedData, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


}
