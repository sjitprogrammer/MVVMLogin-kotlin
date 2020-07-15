package com.example.mvvmlogin.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmlogin.MainActivity
import com.example.mvvmlogin.R
import com.example.mvvmlogin.auth.data.LoginListener
import com.example.mvvmlogin.databinding.ActivityLoginBinding
import com.example.mvvmlogin.util.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),LoginListener {
    private lateinit var viewModel:LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        var viewModelFactory: LoginViewModelFactory = LoginViewModelFactory()
        val viewModel = ViewModelProvider(this,viewModelFactory).get(LoginViewModel::class.java)
        viewModel.loginListener = this
        binding.loginViewModel = viewModel
        binding.setLifecycleOwner(this)
        btn_signup.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun onSuccess() {
        Toast("login success")
        startActivity(Intent(this,MainActivity::class.java))
    }

    override fun onFailure(message: String) {
        Toast(message)
    }
}
