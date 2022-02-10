package com.example.ainterdent.repos

import com.example.ainterdent.model.*
import retrofit2.Call
import retrofit2.http.*

interface EcommerceApi {

    //CATEGORIA
    @GET("categoria/listar")
    suspend fun fetchAllCategories(): List<Categoria>


    //PRODUCTO
    @GET("producto/listar")
    suspend fun fetchAllProducts(): List<Producto>

    @GET("producto/listar/{id}")
    suspend fun fetchProductById(@Path ("id") id:Int ): Producto;

    @GET("producto/listarcategory/{id}")
    suspend fun fetchProductsbyCategory(@Path ("id") id : String ): List<Producto>



    //USUARIOS


    @POST("usuario/registerNewUser")
    suspend fun registerUser(
        @Body user:User
    ):User

    @Headers("Content-Type: application/json")
    @PUT("usuario/update/{id}")
    suspend fun updateUser(
        @Path("id") id:Int , @Body update:User
    ): User


    @GET("usuario/listar/{id}")
    suspend fun findUserById(
        @Path("id") id:Int
    ): User


    //LOGIN
    @POST("authenticate")
    suspend fun fetchlogin(
        @Body request : RequestUser
    ):AuthenticateResponse

}