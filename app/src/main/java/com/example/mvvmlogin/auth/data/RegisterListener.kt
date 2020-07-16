package com.example.mvvmlogin.auth.data

interface RegisterListener {
    fun onSubmit()
    fun onSuccess()
    fun onFailure(message: String)
}