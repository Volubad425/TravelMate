package com.example.travelmateapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.travelmateapp.databinding.ActivityResetPasswordBinding
import java.util.concurrent.TimeUnit

class ResetPasswordActivity : AppCompatActivity(){

    private lateinit var binding: ActivityResetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnChangerMdp.setOnClickListener{
            val mail = binding.addressMail.text.toString()
            val mdp = binding.newPassword.text.toString()
            val new_mdp = binding.confirmNewPassword.text.toString()

            if (mail.isEmpty()){
                Toast.makeText(this, R.string.mail_vide, Toast.LENGTH_SHORT).show()
            }
            else if (!isEmailValid(mail)){
                Toast.makeText(this, R.string.mail_invalide, Toast.LENGTH_SHORT).show()
            }
            else if (mdp.isEmpty()){
                Toast.makeText(this, R.string.password_vide, Toast.LENGTH_SHORT).show()
            }
            else if (new_mdp.isEmpty()){
                Toast.makeText(this, R.string.password_vide, Toast.LENGTH_SHORT).show()
            }
            else if (mdp != new_mdp){
                Toast.makeText(this, R.string.password_confirm_invalide, Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, R.string.mdp_change, Toast.LENGTH_SHORT).show()
                finish()
            }

        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}