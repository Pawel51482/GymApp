package com.example.apk_silownia

import com.example.apk_silownia.model.LoginRequest
import com.example.apk_silownia.model.LoginResponse
import com.example.apk_silownia.model.RegisterRequest
import com.example.apk_silownia.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/login-user/")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

    @POST("/create-user/")
    fun createUser(@Body request: RegisterRequest): Call<RegisterResponse>
}
