package com.example.apk_silownia.model

data class LoginResponse(
    val status_code: Int,
    val message: String,
    val loggedIn: Boolean // To pole wskazuje, czy logowanie się udało
)
