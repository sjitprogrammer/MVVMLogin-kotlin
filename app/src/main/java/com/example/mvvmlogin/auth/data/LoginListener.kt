package com.example.mvvmlogin.auth.data

interface LoginListener {
    fun onSuccess()
    fun onFailure(message: String)
}