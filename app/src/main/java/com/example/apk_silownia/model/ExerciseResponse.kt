package com.example.apk_silownia.model

data class ExerciseResponse(
    val status_code: Int,
    val exercises: List<Exercise> ,
    val success: Boolean,
    val message: String?
)
