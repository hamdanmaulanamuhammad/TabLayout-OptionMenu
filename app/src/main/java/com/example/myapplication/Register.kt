package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding // Menggunakan lateinit var untuk binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate layout menggunakan View Binding
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menggunakan with(binding) untuk akses komponen view
        with(binding) {
            signUpButton.setOnClickListener {
                // Ambil data dari input form
                val name = nameEditText.text.toString().trim()
                val nim = nimEditText.text.toString().trim()
                val email = emailEditText.text.toString().trim()
                val password = passwordEditText.text.toString().trim()

                // Periksa apakah semua field telah diisi
                if (name.isNotEmpty() && nim.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                    // Simpan data pengguna ke SharedPreferences
                    saveUserData(name, nim, email, password)

                    // Tampilkan pesan sukses
                    Toast.makeText(requireContext(), "Registrasi berhasil! Silakan login.", Toast.LENGTH_SHORT).show()

                    // Pindah ke tab Login
                    moveToLoginTab()
                } else {
                    // Tampilkan pesan error jika ada field yang kosong
                    Toast.makeText(requireContext(), "Harap isi semua field!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Fungsi untuk menyimpan data pengguna di SharedPreferences
    private fun saveUserData(name: String, nim: String, email: String, password: String) {
        val sharedPref = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("name", name)
            putString("nim", nim)
            putString("email", email)
            putString("password", password)
            putBoolean("isLoggedIn", false) // Status belum login
            apply() // Simpan perubahan
        }
    }

    // Fungsi untuk berpindah ke tab Login
    private fun moveToLoginTab() {
        val viewPager = requireActivity().findViewById<ViewPager2>(R.id.view_pager)
        viewPager.currentItem = 1 // Pindah ke tab Login (indeks 1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Bersihkan binding untuk mencegah memory leak
        // Tidak perlu _binding = null karena menggunakan lateinit var
    }
}
