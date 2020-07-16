package com.example.mvvmlogin.auth.data

import android.view.View

interface LoginListener {
    fun onSubmit()
    fun onSuccess()
    fun onFailure(message: String)

}