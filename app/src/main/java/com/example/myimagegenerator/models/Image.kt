package com.example.myimagegenerator.models

import java.io.Serializable

data class Image(
    val url: String,
    val original: Boolean,
    val id: String,
    val imagePrompt: String) : Serializable
