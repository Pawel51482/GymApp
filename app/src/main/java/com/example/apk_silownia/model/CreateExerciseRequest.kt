package com.example.apk_silownia.model

data class CreateExerciseRequest(
    val name: String,
    val time: Boolean,
    val reps: Boolean,
    val weight: Boolean
)
