package com.example.mvvmlogin.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

private const val BASE_URL = "http://10.0.1.25/foodorder/public/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object pointing to the desired URL
 * object.
 */
private val retrofit = Retrofit.Builder()
    // TODO (05) Change the ConverterFactory to the MoshiConverterFactory with our Moshi Object
//    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

    @Headers("Content-Type:application/json")
    @POST("api/user/login")
    fun userLogin(@Query("userName") username: String,
                  @Query("passWord") password: String):Call<LoginResponse>

    @Headers("Content-Type:application/json")
    @POST("api/user/register")
    fun userRegister(
        @Query("userName") username: String,
        @Query("passWord") password: String,
        @Query("email") email: String
    ):Call<LoginResponse>
}

object MyApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}