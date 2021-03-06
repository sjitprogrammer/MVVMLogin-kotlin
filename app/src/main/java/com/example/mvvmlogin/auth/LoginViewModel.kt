package com.example.mvvmlogin.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmlogin.auth.data.LoginListener
import com.example.mvvmlogin.network.LoginResponse
import com.example.mvvmlogin.network.MyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()
    private val TAG = "LoginViewModel"
    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var loginListener: LoginListener? = null
    var status = MutableLiveData<Boolean>()
    var hidekeyboard = MutableLiveData<Boolean>()
    init {
        hidekeyboard.value = false
    }
    fun clickLogIn() {
        Log.e(TAG, "clickLogIn : " + username.value)
        hidekeyboard.value = true
        loginListener?.onSubmit()
        if (username.value.isNullOrEmpty() || password.value.isNullOrEmpty()) {
            Log.e(TAG, "Please enter username or password!")
            loginListener?.onFailure("Please enter username or password!")
            return
        }

        MyApi.retrofitService.userLogin(username.value.toString(), password.value.toString())
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e(TAG, "error : " + t)
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.body()?.status!!) {
                        Log.e(TAG, response.body().toString())
                        loginListener?.onSuccess()
                    }else{
                        loginListener?.onFailure(response.body()?.msg.toString())
                    }
                }
            })


    }
}

