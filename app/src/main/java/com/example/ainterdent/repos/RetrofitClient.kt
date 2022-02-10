package com.example.ainterdent.repos

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder

import com.google.gson.Gson

class RetrofitClient {

    private val baseUrl = "http://10.0.2.2:9654/"
    fun getClient(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getClientToken( url : String, token : String) : Retrofit{
        val client = OkHttpClient.Builder()
        client.addInterceptor{ chain ->
            val request = chain.request()
            val newRequest = request.newBuilder().header("Authorization", token)
            chain.proceed(newRequest.build())
        }

    return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}