package com.example.myimagegenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Se Inicializa la Autenticacion de Firebase
        auth = Firebase.auth

        val loginText: TextView = findViewById(R.id.textView_login_here)
        loginText.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        val registerBtn:Button = findViewById<AppCompatButton>(R.id.register_button)

        registerBtn.setOnClickListener{
            registerUser()
        }

    }

    private fun registerUser() {

        // Se almacenan email y password
        val email = findViewById<EditText>(R.id.email_edittext)
        val password = findViewById<EditText>(R.id.password_edittext)

        if (email.text.isNotEmpty() && password.text.isNotEmpty()) {

            val inputEmail = email.text.toString()
            val inputPassword = password.text.toString()

            auth.createUserWithEmailAndPassword(inputEmail,inputPassword)
                .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful){
                        // Registro exitoso se redirige a LoginActivity

                        val intent = Intent(this,LoginActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(
                            baseContext, "Register Successful",
                            Toast.LENGTH_LONG
                        ).show()

                    } else {
                        Toast.makeText(
                            baseContext," Register Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
                .addOnFailureListener {
                    Toast.makeText(this, "Something went wrong!! Please try again ${it.localizedMessage}",
                        Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this,"Please fill all fields",Toast.LENGTH_LONG).show()
        }
    }
}