package com.example.myimagegenerator.services

import com.example.myimagegenerator.viewmodel.UserViewModel
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GPTBuilderApi {
    private const val BASE_URL = "http://localhost:3000/api"
    private val gson = GsonBuilder().setLenient().create()

    private lateinit var userViewModel : UserViewModel

    val interceptor = Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer " + userViewModel.getUserToken())
            .build()

        chain.proceed(newRequest)
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build();


    fun create(): GPTService {
        return retrofit.create(GPTService::class.java)
    }
}