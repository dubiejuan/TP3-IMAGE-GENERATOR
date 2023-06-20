package com.example.myimagegenerator.holders

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myimagegenerator.R

class ImageViewHolder(v: View, private val context: Context) : RecyclerView.ViewHolder(v) {
    private var view: View

    init {
        this.view = v
    }

    fun setPrompt(prompt: String) {
        val txt = this.view.findViewById<TextView>(R.id.txtImageItemPrompt)
        txt.text = prompt
    }

}