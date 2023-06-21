package com.example.myimagegenerator.services

import android.content.Context

import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.GsonBuilder

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GPTBuilderApi {

    private const val IPV4 ="192.168.0.62"
    private const val BASE_URL = "http://$IPV4:3000/api/"
    private val gson = GsonBuilder().setLenient().create()

    fun create(context: Context): GPTService {
         val client = OkHttpClient.Builder().apply {
            addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", getToken(context))
                    .build()

                println(getToken(context))
                chain.proceed(newRequest)
            }
        }.build()

         val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

        return retrofit.create(GPTService::class.java)
    }

    private fun getToken(context: Context): String {
        return runBlocking {
            context.dataStore.data.map { preferences ->
                preferences[stringPreferencesKey("tokenId")].orEmpty()
            }.first()
        }
    }
}