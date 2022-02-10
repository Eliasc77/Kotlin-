package com.example.ainterdent.repos

import com.example.ainterdent.model.*
import retrofit2.Call

class EcommerceRepository {

    private val retrofit = RetrofitClient()
    private val api = retrofit.getClient().create(EcommerceApi::class.java)

    suspend fun fetchAllCategoriesRetrofit() : List<Categoria>{
        return api.fetchAllCategories()
    }

    //PRODUCTOS
    suspend fun fetchAllProducts() : List<Producto>{
        return api.fetchAllProducts()
    }

    suspend fun fetchProductPopular() : List<Producto>{
        return  fetchAllProducts().filter { it.estado.contains("NEW",  true) }
    }
    suspend fun fetchSearchProduct(productName : String): List<Producto>{
        return fetchAllProducts().filter { it.nomPro.contains(productName,true)}

    }
    suspend fun fetchProductByCategoria( id : String) : List<Producto>{
        return api.fetchProductsbyCategory(id)
    }


    //USUARIO

    suspend fun fetchlogin(request : RequestUser) :AuthenticateResponse {
        return api.fetchlogin(request)
    }

    suspend fun registerUser( user : User) : User{
        return api.registerUser(user)
    }

    suspend fun updateUser( id:Int ,user: User): User{
        return api.updateUser(id,user)
    }

    suspend fun findById( id : Int):User{
        return api.findUserById(id)
    }
}