package com.example.myimagegenerator.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myimagegenerator.R
import com.example.myimagegenerator.holders.ImageViewHolder
import com.example.myimagegenerator.models.Image

class ImageAdapter(
    private var images: MutableList<Image>,
    private val context: Context
) : RecyclerView.Adapter<ImageViewHolder>(){

    fun updateData(newImages: MutableList<Image>) {
        images = newImages
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_images_item, parent, false)
        return ImageViewHolder(view,context)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        holder.setPrompt(image.imagePrompt)
        holder.setImage(image.url)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}