package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityDashboardBinding

class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Mengatur View Binding
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur Toolbar sebagai SupportActionBar
        setSupportActionBar(binding.toolbar)

        // Mengatur padding untuk menjaga konten tidak tertutupi oleh sistem bars
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Inflate menu
    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }

    // Menangani item menu yang dipilih
    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                // Ambil data dari SharedPreferences
                val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
                val name = sharedPref.getString("name", "Nama tidak tersedia")
                val nim = sharedPref.getString("nim", "NIM tidak tersedia")

                // Pindah ke Profile Activity dan mengirim data
                val intent = Intent(this, Profile::class.java).apply {
                    putExtra("name", name)
                    putExtra("nim", nim)
                }
                startActivity(intent) // Pindah ke Profile
                true
            }
            R.id.action_logout -> {
                // Logika untuk logout
                Toast.makeText(this, "Berhasil Logout!", Toast.LENGTH_SHORT).show()

                // Kembali ke halaman MainActivity (yang menampilkan Register dan Login)
                val intent = Intent(this, MainActivity::class.java) // Kembali ke MainActivity
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK) // Bersihkan stack
                startActivity(intent) // Kembali ke MainActivity
                finish() // Tutup aktivitas saat ini
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

