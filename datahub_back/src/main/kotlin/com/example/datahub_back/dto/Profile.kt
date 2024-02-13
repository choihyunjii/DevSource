package com.example.datahub_back.dto

data class Profile(
    val id : Long,
    val username : String,
    val phoneNumber : String,
    val email : String,
    val password : String,
    val project : MutableList<Project>
)
