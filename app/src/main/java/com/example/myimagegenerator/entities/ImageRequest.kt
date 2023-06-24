package com.example.myimagegenerator.entities

import java.io.Serializable

data class ImageRequest(
    val id: String?,
    val imagePrompt: String?,
):Serializable
