package com.example.mvvmlogin.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmlogin.MainActivity
import com.example.mvvmlogin.R
import com.example.mvvmlogin.auth.data.RegisterListener
import com.example.mvvmlogin.databinding.ActivityRegisterBinding
import com.example.mvvmlogin.util.Toast
import com.example.mvvmlogin.util.hide
import com.example.mvvmlogin.util.show
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val binding : ActivityRegisterBinding = DataBindingUtil.setContentView(this,R.layout.activity_register)
        var viewModelFactory:RegisterViewModelFactory = RegisterViewModelFactory()
        val viewModel = ViewModelProvider(this,viewModelFactory).get(RegisterViewModel::class.java)
        viewModel.registerListener = this
//        binding. = viewModel
        binding.registerViewModel = viewModel
        binding.setLifecycleOwner(this)
        arrow_back.setOnClickListener {
            finish();
        }
    }

    override fun onSubmit() {
        progressBar3.show()
    }

    override fun onSuccess() {
        progressBar3.hide()
        Toast("Register success")
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onFailure(message: String) {
        progressBar3.hide()
        Toast(message)
    }
}
