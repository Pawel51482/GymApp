package com.example.apk_silownia.model

data class Exercise(
    val id: Int,           // Unikalny identyfikator ćwiczenia
    val name: String,      // Nazwa ćwiczenia (np. Push Up)
    val category: String   // Kategoria ćwiczenia (np. Upper Body, Lower Body)
)
