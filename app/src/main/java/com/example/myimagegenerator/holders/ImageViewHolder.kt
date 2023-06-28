package com.example.myimagegenerator.holders

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myimagegenerator.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ImageViewHolder(v: View, private val context: Context) : RecyclerView.ViewHolder(v) {
    private var view: View
    private val imageView: ImageView = itemView.findViewById(R.id.myImgItem)
    private var BASEURL =  "https://searchu.s3.amazonaws.com"


    init {
        this.view = v
    }

    fun setPrompt(prompt: String) {
        val txt = this.view.findViewById<TextView>(R.id.txtMyImageItemPrompt)
        txt.text = prompt
    }

    fun setImage(imageUrl: String) {
        Log.d("ImageHolder", "Image URL: $imageUrl") // Log the imageUrl
        val finalURL = BASEURL + imageUrl;
        Picasso.get().load(finalURL).into(imageView, object : Callback {
            override fun onSuccess() {
                // Image loaded successfully
                Log.println(Log.INFO,"ImageLoadingSuccess", "Image loaded successfully")
            }

            override fun onError(e: Exception) {
                Log.e("ImageLoadingError", e.message, e)
            }
        })
    }

    }
