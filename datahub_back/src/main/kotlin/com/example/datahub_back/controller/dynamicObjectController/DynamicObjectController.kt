package com.example.datahub_back.controller.dynamicObjectController

import com.example.datahub_back.service.daynamicObjectService.DynamicObjectService
import com.fasterxml.jackson.databind.JsonNode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/source")
@CrossOrigin(origins = ["http://localhost:3000"])
class DynamicObjectController (
    @Autowired
    private val dynamicObjectService: DynamicObjectService
){
    @GetMapping("/table/hash/{tableID}")
    fun getTableHash(@PathVariable tableID : Long) : String? =
        dynamicObjectService.getTableHash(tableID)

    @GetMapping("/{tableHash}")
    fun getAllTableData(@PathVariable tableHash: String) : List<JsonNode> =
        dynamicObjectService.tableDataConvertJsonNode(tableHash)

    @PostMapping("/{tableHash}")
    fun createDynamicObject(@PathVariable tableHash: String, @RequestBody data: JsonNode): Any {
        return dynamicObjectService.createDynamicObject(tableHash, data)
    }

    @GetMapping("/{tableHash}/data/{id}")
    fun getDynamicObject(@PathVariable tableHash: String, @PathVariable id: Long): Any {
        return dynamicObjectService.getDynamicObject(tableHash, id)
    }


    // UPDATE
    @PutMapping("/{tableHash}/data/{id}")
    fun updateDynamicObject(@PathVariable tableHash: String, @PathVariable id: Long, @RequestBody updateData: JsonNode): Any {
        return dynamicObjectService.updateDynamicObject(tableHash, id, updateData)
    }

    // DELETE
    @DeleteMapping("/{tableHash}/data/{id}")
    fun deleteDynamicObject(@PathVariable tableHash: String, @PathVariable id: Long) {
        dynamicObjectService.deleteDynamicObject(tableHash, id)
    }


}