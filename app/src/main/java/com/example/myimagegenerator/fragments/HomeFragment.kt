package com.example.myimagegenerator.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myimagegenerator.R
import com.example.myimagegenerator.adapters.ImageAdapter
import com.example.myimagegenerator.models.Image
import com.example.myimagegenerator.services.GPTBuilderApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    var images: MutableList<Image> = ArrayList<Image>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val imageAdapter = ImageAdapter(images, requireContext())

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerViewImages = view.findViewById<RecyclerView>(R.id.recyclerViewMyImages)
        recyclerViewImages.adapter = imageAdapter

        val linearLayoutManager = LinearLayoutManager(context)
        recyclerViewImages.layoutManager = linearLayoutManager

        GPTBuilderApi.create(requireContext()).getImages().enqueue(object : Callback<List<Image>> {
            override fun onResponse(call: Call<List<Image>>, response: Response<List<Image>>) {

                if (response.isSuccessful) {
                    images = response.body() as MutableList<Image>
                    imageAdapter.updateData(images)
                    imageAdapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<List<Image>>, t: Throwable) {
                Log.e("Example", t.stackTraceToString())
            }
        })

        return view
    }
}