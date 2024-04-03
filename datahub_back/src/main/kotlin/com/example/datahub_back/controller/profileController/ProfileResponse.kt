package com.example.datahub_back.controller.profileController

data class ProfileResponse(
    val name: String,
    val phoneNum: String,
    val email: String,
    val pw: String,
) {
}