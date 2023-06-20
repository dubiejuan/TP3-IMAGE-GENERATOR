package com.example.myimagegenerator.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myimagegenerator.R
import com.example.myimagegenerator.adapters.ImageAdapter
import com.example.myimagegenerator.models.Image

class GeneratorFragment : Fragment() {

    var images: MutableList<Image> = ArrayList<Image>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_generator, container, false)

        val recyclerViewImages = view.findViewById<RecyclerView>(R.id.recyclerViewGeneratedImage)
        val linearLayoutManager = LinearLayoutManager(context)

        recyclerViewImages.adapter = ImageAdapter(images, context!!)
        recyclerViewImages.layoutManager = linearLayoutManager

        return view
    }

}