package com.example.mvvmlogin.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmlogin.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        arrow_back.setOnClickListener {
            finish();
        }
    }
}
