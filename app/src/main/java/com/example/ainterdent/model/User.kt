package com.example.ainterdent.model

data class User(
    val codUsu: Int,
    val apellidos: Any?,
    val direccion: Any?,
    val distrito: Any?,
    val dni: String?,
    val email: String,
    val nombres: String?,
    val password: String,
    val rol: List<Rol>?,
    val telefono: String?,
    val imageUrl: String?
)