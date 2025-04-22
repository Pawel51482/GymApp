package com.example.apk_silownia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apk_silownia.ui.ProfileFragment
import kotlin.jvm.java

class RegisterActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private val authManager = AuthManager() // Tworzymy instancję AuthManagera z kontekstem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        usernameEditText = findViewById(R.id.usernameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        registerButton = findViewById(R.id.registerButton)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                registerUser(username, email, password)
            } else {
                Toast.makeText(this, "Proszę uzupełnić wszystkie pola", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(username: String, email: String, password: String) {
        // Używamy AuthManagera do rejestracji
        authManager.registerUser(username, email, password) { success, message ->
            if (success) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

                // Po udanej rejestracji, przechodzimy do LoginActivity
                val intent = Intent(this, ProfileFragment::class.java)
                startActivity(intent)
                finish() // Kończymy RegisterActivity
            } else {
                Toast.makeText(this, message ?: "Błąd rejestracji", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
