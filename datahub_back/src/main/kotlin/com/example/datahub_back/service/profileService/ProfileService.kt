package com.example.datahub_back.service.profileService

import com.example.datahub_back.data.toolData.exampleProfile
import com.example.datahub_back.dto.toolDTO.Profile
import org.springframework.stereotype.Service

@Service
class ProfileService {

    private val profileList = listOf(
        Profile(1, "사용자1", "010-1234-5678", "user1@example.com", "비밀번호1"),
        Profile(2, "사용자2", "010-2345-6789", "user2@example.com", "비밀번호2"),
        Profile(3, "사용자3", "010-3456-7890", "user3@example.com", "비밀번호3"),
        Profile(4, "사용자4", "010-4567-8901", "user4@example.com", "비밀번호4"),
        Profile(5, "사용자5", "010-5678-9012", "user5@example.com", "비밀번호5")
    )
    fun getAllProfiles() = profileList

    fun getProfileById(id: Long) : Profile? = profileList.find { it.id == id }


}