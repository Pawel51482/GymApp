package com.example.apk_silownia

import com.example.apk_silownia.model.CreateExerciseRequest
import com.example.apk_silownia.model.ExerciseResponse
import com.example.apk_silownia.model.LoginRequest
import com.example.apk_silownia.model.LoginResponse
import com.example.apk_silownia.model.RegisterRequest
import com.example.apk_silownia.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("/login-user/")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

    @POST("/create-user/")
    fun createUser(@Body request: RegisterRequest): Call<RegisterResponse>

    @GET("/get-exercises/")
    fun getExercises(): Call<ExerciseResponse>

    @POST("/create-exercise/")
    fun createExercise(@Body request: CreateExerciseRequest): Call<ExerciseResponse>

    @DELETE("/delete-exercise/{id}/")
    fun deleteExercise(@Path("id") exerciseId: Int): Call<ExerciseResponse>

    @PUT("/update-exercise/{id}/")
    fun updateExerciseName(@Path("id") exerciseId: Int, @Body updatedExercise: CreateExerciseRequest
    ): Call<ExerciseResponse>
}
