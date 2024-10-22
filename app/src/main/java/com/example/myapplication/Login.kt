package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.content.Intent
import com.example.myapplication.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding // Menggunakan lateinit var untuk binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate layout menggunakan View Binding
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menggunakan with(binding) untuk akses komponen view
        with(binding) {
            loginButton.setOnClickListener {
                // Ambil input dari email dan password
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
                    navigateToDashboard()
                } else {
                    // Login gagal, tampilkan pesan error
                    Toast.makeText(requireContext(), "Email atau password salah!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Fungsi untuk berpindah ke Dashboard
    private fun navigateToDashboard() {
        val intent = Intent(requireActivity(), Dashboard::class.java)
        startActivity(intent)
        requireActivity().finish() // Tutup activity saat ini
    }
}
