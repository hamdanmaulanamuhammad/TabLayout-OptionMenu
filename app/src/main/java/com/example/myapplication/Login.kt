package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent

class LoginFragment : Fragment() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        emailEditText = view.findViewById(R.id.emailEditText) // Pastikan ID sesuai
        passwordEditText = view.findViewById(R.id.passwordEditText) // Pastikan ID sesuai
        loginButton = view.findViewById(R.id.loginButton) // Pastikan ID sesuai

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Cek apakah email dan password tidak kosong
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Harap isi email dan password!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Cek kredensial pengguna di SharedPreferences
            val sharedPref = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)
            val savedEmail = sharedPref.getString("email", null)
            val savedPassword = sharedPref.getString("password", null)

            // Validasi apakah email dan password cocok
            if (email == savedEmail && password == savedPassword) {
                // Login berhasil, arahkan ke Dashboard
                Toast.makeText(requireContext(), "Login berhasil!", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireActivity(), Dashboard::class.java)
                startActivity(intent)
                requireActivity().finish() // Tutup LoginActivity
            } else {
                // Login gagal, tampilkan pesan error
                Toast.makeText(requireContext(), "Email atau password salah!", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
