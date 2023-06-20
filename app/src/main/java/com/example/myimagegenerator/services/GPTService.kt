package com.example.myimagegenerator.services

import android.media.Image
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface GPTService {

    @GET("/image")
    fun getImages(): Call<List<Image>>

    @POST("/image/generate")
    fun generateImage(): Call<List<Image>>
}