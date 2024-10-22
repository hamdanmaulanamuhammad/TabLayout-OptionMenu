package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityDashboardBinding

class Dashboard : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding // Inisialisasi View Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Mengatur View Binding
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Gunakan with(binding) untuk mengakses komponen UI dengan binding
        with(binding) {
            // Mengatur Toolbar sebagai SupportActionBar
            setSupportActionBar(toolbar)
        }
    }

    // Inflate menu options untuk Toolbar
    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }

    // Menangani item menu yang dipilih
    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                navigateToProfile() // Panggil fungsi untuk berpindah ke halaman Profile
                true
            }
            R.id.action_logout -> {
                performLogout() // Panggil fungsi untuk logout
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Fungsi untuk berpindah ke Profile Activity
    private fun navigateToProfile() {
        // Ambil data dari SharedPreferences
        val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
        val name = sharedPref.getString("name", "Nama tidak tersedia")
        val nim = sharedPref.getString("nim", "NIM tidak tersedia")

        // Pindah ke Profile Activity dan kirim data
        val intent = Intent(this, Profile::class.java).apply {
            putExtra("name", name)
            putExtra("nim", nim)
        }
        startActivity(intent)
    }

    // Fungsi untuk logout
    private fun performLogout() {
        // Tampilkan pesan logout
        Toast.makeText(this, "Berhasil Logout!", Toast.LENGTH_SHORT).show()

        // Kembali ke MainActivity (halaman Register dan Login)
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK) // Bersihkan stack aktivitas
        }
        startActivity(intent)
        finish() // Tutup Dashboard
    }
}
