package com.example.adminsrirejeki.DatabaseTest

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.adminsrirejeki.databinding.ActivityTestBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi Realtime Database
        database = FirebaseDatabase.getInstance().reference

        // Tombol test kirim data
        binding.btnTestKirim.setOnClickListener {
            kirimDataTes()
        }

    }

    private fun kirimDataTes() {
        val dataDummy = hashMapOf(
            "nama" to "Yudha",
            "waktu" to System.currentTimeMillis(),
            "status" to "Test Database"
        )

        // Kirim data ke Realtime Database
        database.child("testdatabase").push()
            .setValue(dataDummy)
            .addOnSuccessListener {
                Log.d("FirebaseTest", "Berhasil dikirim ke Realtime Database")
            }
            .addOnFailureListener { e ->
                Log.e("FirebaseTest", "Gagal mengirim data", e)
            }
    }
}