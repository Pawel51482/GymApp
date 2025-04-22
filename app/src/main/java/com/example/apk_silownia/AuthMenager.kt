package com.example.apk_silownia

import com.example.apk_silownia.model.LoginRequest
import com.example.apk_silownia.model.LoginResponse
import com.example.apk_silownia.model.RegisterRequest
import com.example.apk_silownia.model.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthManager() {

    var isLoggedIn: Boolean = false
    var userEmail: String? = null

    // Logowanie użytkownika
    fun loginUser(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        val loginRequest = LoginRequest(email, password)

        RetrofitClient.apiService.loginUser(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    // Jeżeli logowanie się udało, zapisujemy stan
                    val loggedIn = response.body()?.loggedIn ?: false
                    isLoggedIn = loggedIn
                    userEmail = if (loggedIn) email else null

                    // Przekazujemy odpowiedni komunikat
                    callback(true, "Zalogowano pomyślnie")
                } else {
                    callback(false, "Błąd logowania: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback(false, "Błąd logowania: ${t.message}")
            }
        })
    }

    // Rejestracja użytkownika
    fun registerUser(username: String, email: String, password: String, callback: (Boolean, String?) -> Unit) {
        val registerRequest = RegisterRequest(username, email, password)

        RetrofitClient.apiService.createUser(registerRequest).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    callback(true, "Zarejestrowano pomyślnie")
                } else {
                    callback(false, "Błąd rejestracji: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                callback(false, "Błąd rejestracji: ${t.message}")
            }
        })
    }

    // Funkcja do wylogowania
    fun logout() {
        isLoggedIn = false
        userEmail = null
    }
}