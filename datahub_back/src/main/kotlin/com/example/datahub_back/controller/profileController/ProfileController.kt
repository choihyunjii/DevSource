package com.example.datahub_back.controller.profileController

import com.example.datahub_back.dto.toolDTO.Profile
import com.example.datahub_back.service.profileService.ProfileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

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
    @PostMapping
    fun createProfile(@RequestBody profileResponse: ProfileResponse) =
       println(profileResponse)

}
