package com.example.apk_silownia.model

data class ExerciseResponse(
    val status_code: Int,      // Kod statusu odpowiedzi
    val exercises: List<Exercise>  // Lista ćwiczeń
)
