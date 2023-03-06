package com.example.travelmateapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.travelmateapp.databinding.ActivityConnectionBinding

class ConnectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConnectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConnectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConnexion.setOnClickListener {
            val mail = binding.addressMail.text.toString()
            val mdp = binding.motDePasse.text.toString()

            if(mail.isEmpty()){
                Toast.makeText(this, R.string.mail_vide, Toast.LENGTH_SHORT).show()
            }
            /*else if(!isMailValid(mail)){
                Toast.makeText(this, "veuillez ajouter un email valide", Toast.LENGTH_SHORT).show()
            }*/
            else if(mdp.isEmpty()){
                Toast.makeText(this, R.string.password_vide, Toast.LENGTH_SHORT).show()
            }
            else if (mdp == "a" && mail == "a"){
                val intent = Intent(this, MapsActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, R.string.non_compte, Toast.LENGTH_SHORT).show()
            }
        }

        binding.mdpOublie.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Check if the mail is valid
     */
    private fun isMailValid(mail: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(mail).matches()
    }
}