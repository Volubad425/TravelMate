package com.example.travelmateapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.travelmateapp.databinding.ActivityResetPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class ResetPasswordActivity : AppCompatActivity(){

    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.btnChangerMdp.setOnClickListener{
            val mail = binding.addressMail.text.toString()


            if (mail.isEmpty()){
                Toast.makeText(this, R.string.mail_vide, Toast.LENGTH_SHORT).show()
            }
            else if (!isEmailValid(mail)){
                Toast.makeText(this, R.string.mail_invalide, Toast.LENGTH_SHORT).show()
            }
            else {
                resetPassword(mail)
            }

        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun resetPassword(email:String){
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        baseContext, "Un mail vous a été envoyé, veuillez vérifier votre boite mail",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        baseContext, "Erreur.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
            .addOnFailureListener{
                Toast.makeText(this,"Une erreur a été détecté ${it.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }
    }
}