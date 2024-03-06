package com.example.datahub_back.controller.profileController

import com.example.datahub_back.dto.toolDTO.Profile
import com.example.datahub_back.service.profileService.ProfileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = ["http://localhost:3000"])
class ProfileController (
    @Autowired
    private val profileService: ProfileService
){

    @GetMapping
    fun getAllProfiles() : List<Profile> =
        profileService.getAllProfiles()

}