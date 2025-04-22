package com.example.apk_silownia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.apk_silownia.ui.ProfileFragment

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var goToRegisterText: TextView
    private lateinit var authManager: AuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        // Inicjalizujemy AuthManager
        authManager = AuthManager()

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        goToRegisterText = findViewById(R.id.goToRegisterText)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Proszę uzupełnić wszystkie pola", Toast.LENGTH_SHORT).show()
            }
        }

        goToRegisterText.setOnClickListener {
            // Przejście do ekranu rejestracji
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, password: String) {
        authManager.loginUser(email, password) { success, message ->
            if (success) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

                // Po zalogowaniu, przechodzimy do ProfileFragment
                if (authManager.isLoggedIn) {
                    val intent = Intent(this, ProfileFragment::class.java)
                    startActivity(intent)
                    finish() // Kończymy LoginActivity
                }
            } else {
                Toast.makeText(this, message ?: "Błąd logowania", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
