package com.example.myimagegenerator.services

import com.example.myimagegenerator.models.Image
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface GPTService {
    @GET("image")
    fun getImages(): Call<List<Image>>
    @POST("image/generate")
    fun generateImage(imagePrompt:String): Call<List<Image>>

    @POST("image/variant")
    fun generateVariant(id:String): Call<List<Image>>

    @POST("image")
    fun saveImage(id:String): Call<Void>
}