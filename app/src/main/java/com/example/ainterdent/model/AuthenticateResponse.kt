package com.example.ainterdent.model

data class AuthenticateResponse(
    val jwttoken: String,
    val user: User
)