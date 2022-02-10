package com.example.ainterdent.model

data class Producto(
    val categoria: Categoria,
    val codPro: Int,
    val estado: String,
    val imgUrl: String,
    val isOnSale: Boolean,
    val nomPro: String,
    val precioUnit: Double,
    val stock: Int,
    val description:String
)