package com.example.myimagegenerator.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myimagegenerator.R
import com.example.myimagegenerator.entities.ImageRequest
import com.example.myimagegenerator.models.Image
import com.example.myimagegenerator.services.GPTBuilderApi
import androidx.appcompat.widget.AppCompatEditText
import com.example.myimagegenerator.adapters.TemporaryAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.navigation.fragment.findNavController


class GeneratorFragment : Fragment(), TemporaryAdapter.OnImageClickListener {

    var images: MutableList<Image> = ArrayList<Image>()

    lateinit var generateImageButton: Button
    lateinit var generateVariantImageButton: Button
    lateinit var saveImageButton: Button
    lateinit var temporaryAdapter : TemporaryAdapter

    lateinit var imagePrompt: AppCompatEditText

    lateinit var idSelectedImage: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        temporaryAdapter = TemporaryAdapter(images, requireContext(), this)

        val view = inflater.inflate(R.layout.fragment_generator, container, false)

        generateImageButton = view.findViewById(R.id.btnGeneratorFragmentGenerate)
        generateVariantImageButton = view.findViewById(R.id.btnFragmentGeneratorVariant)
        saveImageButton = view.findViewById(R.id.btnFragmentGeneratorSave)
        imagePrompt = view.findViewById(R.id.inputGeneratorFragment)

        generateVariantImageButton.isEnabled = false
        saveImageButton.isEnabled = false


        val recyclerViewImages = view.findViewById<RecyclerView>(R.id.recyclerViewTemporary)

        recyclerViewImages.adapter = temporaryAdapter

        val linearLayoutManager = LinearLayoutManager(context)
        recyclerViewImages.layoutManager = linearLayoutManager

        generateImageButton.setOnClickListener {
            generateImage()
        }

        saveImageButton.setOnClickListener {
            saveImage()
        }

        generateVariantImageButton.setOnClickListener {
            generateVariantImage()
        }

        return view
    }


    override fun onStart() {
        super.onStart()

    }


    override fun onImageClick(id: String?) {
        idSelectedImage = id!!;
        generateVariantImageButton.isEnabled = true
        saveImageButton.isEnabled = true

    }

    private fun generateImage(){
        val imagePromptText = imagePrompt.text.toString()
        if(imagePromptText.isNullOrEmpty()){
            Toast.makeText(requireActivity(), "No se han completado el campo requerido", Toast.LENGTH_SHORT).show()
        }else{
            val imageRequest = ImageRequest(id = null, imagePrompt = imagePromptText)

            GPTBuilderApi.create(requireContext()).generateImage(imageRequest).enqueue(object :
                Callback<List<Image>> {
                override fun onResponse(call: Call<List<Image>>, response: Response<List<Image>>) {
                    if (response.isSuccessful) {
                        images = response.body() as MutableList<Image>
                        temporaryAdapter.setData(images)
                    }
                }
                override fun onFailure(call: Call<List<Image>>, t: Throwable) {
                    Log.e("Example", t.stackTraceToString())
                }
            })
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun saveImage() {

        findNavController().navigate(R.id.fragment_home)
        var imageRequest = ImageRequest(idSelectedImage, imagePrompt = null)

            GPTBuilderApi.create(requireContext()).saveImage(imageRequest).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    findNavController().navigate(R.id.fragment_home)

                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.e("Example", t.stackTraceToString())
                }
            })
    }

    private fun generateVariantImage(){
        var imageRequest = ImageRequest(idSelectedImage, imagePrompt = null)

        GPTBuilderApi.create(requireContext()).generateVariant(imageRequest).enqueue(object :
            Callback<List<Image>> {
            override fun onResponse(call: Call<List<Image>>, response: Response<List<Image>>) {
                if (response.isSuccessful) {
                    images = response.body() as MutableList<Image>
                   temporaryAdapter.setData(images)
                }
            }
            override fun onFailure(call: Call<List<Image>>, t: Throwable) {
                Log.e("Example", t.stackTraceToString())
            }
        })
    }
}



