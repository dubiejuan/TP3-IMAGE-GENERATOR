package com.example.myimagegenerator.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myimagegenerator.R
import com.example.myimagegenerator.holders.ImageViewHolder
import com.example.myimagegenerator.models.Image

class TemporaryAdapter(
    private var images: MutableList<Image>,
    private val context: Context,
    private val listener: OnImageClickListener
) : RecyclerView.Adapter<ImageViewHolder>(){

    fun updateData(newImages: MutableList<Image>) {
        images = newImages
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item_recycler_view, parent, false)
        return ImageViewHolder(view,context)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        holder.setPrompt(image.imagePrompt)
        holder.setImage(image.url)

        val imageId = image.id // Obtener el ID de la imagen

        // Establecer el Listener de clic en la imagen
        holder.itemView.setOnClickListener {
            listener.onImageClick(imageId) // Pasar el ID de la imagen al método de callback
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    interface OnImageClickListener {
        fun onImageClick(id: String?)
    }
}