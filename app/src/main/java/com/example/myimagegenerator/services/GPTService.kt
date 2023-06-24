package com.example.myimagegenerator.services

import com.example.myimagegenerator.entities.ImageRequest
import com.example.myimagegenerator.models.Image
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GPTService {
    @GET("image")
     fun getImages(): Call<List<Image>>
    @POST("image/generate")
     fun generateImage(@Body body: ImageRequest): Call<List<Image>>

    @POST("image/variant")
     fun generateVariant(@Body body: ImageRequest): Call<List<Image>>

    @POST("image")
     fun saveImage(@Body body: ImageRequest): Call<Void>
}