package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    // Menentukan jumlah fragment yang akan ditampilkan (Register dan Login)
    override fun getItemCount(): Int {
        return 2 // Dua fragment: RegisterFragment dan LoginFragment
    }

    // Mengembalikan fragment yang sesuai berdasarkan posisi
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RegisterFragment() // Mengembalikan fragment Register
            1 -> LoginFragment()    // Mengembalikan fragment Login
            else -> throw IllegalStateException("Unexpected position $position") // Keamanan
        }
    }
}
