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

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.signUpButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val nim = binding.nimEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (name.isNotEmpty() && nim.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                // Simpan data pengguna di SharedPreferences
                val sharedPref = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putString("name", name)
                    putString("nim", nim)
                    putString("email", email)
                    putString("password", password)
                    putBoolean("isLoggedIn", false) // Belum login
                    apply()
                }

                // Menampilkan pesan sukses
                Toast.makeText(requireContext(), "Registrasi berhasil! Silakan login.", Toast.LENGTH_SHORT).show()

                // Arahkan ke tab Login
                val viewPager = requireActivity().findViewById<ViewPager2>(R.id.view_pager)
                viewPager.currentItem = 1 // Pindah ke tab Login
            } else {
                Toast.makeText(requireContext(), "Harap isi semua field!", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Membersihkan binding untuk mencegah memory leak
    }
}
