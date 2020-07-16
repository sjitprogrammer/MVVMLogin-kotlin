package com.example.mvvmlogin.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(val id: Int, val display_name: String, val username: String, val email: String?=null)