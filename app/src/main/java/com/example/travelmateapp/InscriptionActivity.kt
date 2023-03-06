package com.example.travelmateapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.travelmateapp.databinding.ActivityInscriptionBinding

class InscriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInscriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInscriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnInsription.setOnClickListener{
            val pseudo = binding.pseudo.text.toString()
            val mail = binding.addressMail.text.toString()
            val password = binding.password.text.toString()
            val passwordConfirm = binding.passwordConfirm.text.toString()

            if (pseudo.isEmpty()){
                Toast.makeText(this, R.string.pseudo_vide, Toast.LENGTH_SHORT).show()
            }
            else if(pseudo.length > 19){
                Toast.makeText(this, R.string.pseudo_trop_long, Toast.LENGTH_SHORT).show()
            }
            else if(mail.isEmpty()){
                Toast.makeText(this, R.string.mail_vide, Toast.LENGTH_SHORT).show()
            }
            else if(!isMailValid(mail)){
                Toast.makeText(this, R.string.mail_invalide, Toast.LENGTH_SHORT).show()
            }
            else if(password.isEmpty()){
                Toast.makeText(this, R.string.password_vide, Toast.LENGTH_SHORT).show()
            }
            /*else if(password.length < 8){
                Toast.makeText(this, R.string.password_trop_court, Toast.LENGTH_SHORT).show()
            }*/
            else if(passwordConfirm.isEmpty()){
                Toast.makeText(this, R.string.password_vide, Toast.LENGTH_SHORT).show()
            }
            else if(password != passwordConfirm){
                Toast.makeText(this, R.string.password_confirm_invalide, Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, R.string.inscription_reussie, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isMailValid(mail: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()
    }
}