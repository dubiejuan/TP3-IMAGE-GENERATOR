package com.example.myimagegenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.example.myimagegenerator.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var userViewModel : UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializo Autenticacion de Firebase
        auth = Firebase.auth

        val registerText:TextView = findViewById(R.id.textView_register_here)
        registerText.setOnClickListener{
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        val loginBtn : Button = findViewById(R.id.login_button)

        loginBtn.setOnClickListener{
            loginUser()
        }
    }

    private fun loginUser() {
        val email = findViewById<EditText>(R.id.email_edittext)
        val password = findViewById<EditText>(R.id.password_edittext)

        if (email.text.isNotEmpty() && password.text.isNotEmpty()){
            val inputEmail = email.text.toString()
            val inputPassword = password.text.toString()



            auth.signInWithEmailAndPassword(inputEmail,inputPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful){

                        // Obtener el token de usuario
                        val currentUser = FirebaseAuth.getInstance().currentUser
                        currentUser?.getIdToken(true)
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {

                                    val token = task.result?.token.toString()
                                    userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
                                    userViewModel.setUserToken(token)

                                    // Usar la siguiente sentencia en donde se necesite el token para obtenerlo
                                    // val userToken = userViewModel.getUserToken()

                                    Toast.makeText(this, "Token de Usuario: ${userViewModel.getUserToken()}", Toast.LENGTH_SHORT).show()
                                } else {
                                    Log.w("Firebase", "Error al obtener el token de usuario.", task.exception)
                                }
                            }

                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)

                        Toast.makeText(
                            baseContext, "Login Successful",
                            Toast.LENGTH_LONG
                        ).show()

                    } else {
                        Toast.makeText(
                            baseContext," Login Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .addOnFailureListener{
                    Toast.makeText(this, "Something went wrong!! Please try again ${it.localizedMessage}",
                        Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this,"Please fill all fields", Toast.LENGTH_LONG).show()
        }
    }
}