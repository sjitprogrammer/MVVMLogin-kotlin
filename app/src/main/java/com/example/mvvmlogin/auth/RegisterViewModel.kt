package com.example.mvvmlogin.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmlogin.auth.data.RegisterListener
import com.example.mvvmlogin.network.LoginResponse
import com.example.mvvmlogin.network.MyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val TAG = "RegisterViewModel"

class RegisterViewModel : ViewModel() {
    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var confirm_password = MutableLiveData<String>()
    var email = MutableLiveData<String>()

    var hidekeyboard = MutableLiveData<Boolean>()
    var registerListener: RegisterListener? = null

    init {
        hidekeyboard.value = false
    }

    fun onClickRegister() {
        hidekeyboard.value = true
        registerListener?.onSubmit()
        Log.e(TAG, "onClickRegister")
        if (!password.value.equals(confirm_password.value)) {
            Log.e(TAG, "Password and confirm password not match!")
            registerListener?.onFailure("Password and confirm password not match!")
            return
        }
        if (username.value.isNullOrEmpty() || password.value.isNullOrEmpty()) {
            Log.e(TAG, "Please enter username or password!")
            registerListener?.onFailure("Please enter username or password!")
            return
        }

        MyApi.retrofitService.userRegister(
            username.value.toString(),
            password.value.toString(),
            email.value.toString()
        )
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
                        registerListener?.onSuccess()
                    } else {
                        registerListener?.onFailure(response.body()?.msg.toString())
                    }
                }
            })
    }
}