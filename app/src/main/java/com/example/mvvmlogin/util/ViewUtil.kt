package com.example.mvvmlogin.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
fun Context.Toast(message:String){
    Toast.makeText(this,message, android.widget.Toast.LENGTH_LONG).show()
}


fun ProgressBar.show(){
    visibility = View.VISIBLE
}

fun ProgressBar.hide(){
    visibility = View.GONE
}