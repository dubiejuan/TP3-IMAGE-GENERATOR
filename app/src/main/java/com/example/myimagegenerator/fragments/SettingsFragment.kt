package com.example.myimagegenerator.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.example.myimagegenerator.R
import com.example.myimagegenerator.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SettingsFragment : Fragment() {

    private var auth: FirebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val btnLogout: AppCompatButton = view.findViewById(R.id.logout_button)

        btnLogout.setOnClickListener{
            logout()
            val intent = Intent(this.context, LoginActivity::class.java)
            startActivity(intent)
        }

        return view
    }
    private fun logout(){
        auth.signOut()
    }
}