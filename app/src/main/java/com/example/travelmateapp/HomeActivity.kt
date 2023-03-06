package com.example.travelmateapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.travelmateapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity()  {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.seConnecter.setOnClickListener {
            val intent = Intent(this, ConnectionActivity::class.java)
            startActivity(intent)
        }

        binding.sInscrire.setOnClickListener {
            val intent = Intent(this, InscriptionActivity::class.java)
            startActivity(intent)
        }
    }
}