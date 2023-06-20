package com.example.myimagegenerator.viewmodel

import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private var userToken: String = ""

    fun setUserToken(token: String) {
        userToken = token
    }

    fun getUserToken(): String {
        return userToken
    }
}