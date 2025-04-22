package com.example.apk_silownia.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.apk_silownia.LoginActivity
import com.example.apk_silownia.R
import com.example.apk_silownia.AuthManager

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var profileTextView: TextView
    private lateinit var logoutButton: Button
    private val authManager = AuthManager() // Tworzymy instancję AuthManagera

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)

        // Inicjalizujemy widoki
        profileTextView = rootView.findViewById(R.id.profileTextView)
        logoutButton = rootView.findViewById(R.id.logoutButton)

        // Sprawdzamy, czy użytkownik jest zalogowany
        if (authManager.isLoggedIn) {
            val email = authManager.userEmail
            profileTextView.text = "Jesteś zalogowany jako: $email"
            logoutButton.text = "Wyloguj się"
        } else {
            profileTextView.text = "Nie jesteś zalogowany"
            logoutButton.text = "Zaloguj się"
        }

        // Wylogowanie użytkownika
        logoutButton.setOnClickListener {
            if (authManager.isLoggedIn) {
                authManager.logout()  // Wyloguj użytkownika
                Toast.makeText(requireContext(), "Wylogowano", Toast.LENGTH_SHORT).show()

                // Przekierowanie na ekran logowania
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish() // Kończymy ProfileFragment
            } else {
                // Jeśli nie jesteś zalogowany, przekieruj na LoginActivity
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
            }
        }

        return rootView
    }
}
