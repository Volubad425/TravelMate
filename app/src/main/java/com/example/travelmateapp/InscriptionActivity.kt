package com.example.travelmateapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.travelmateapp.databinding.ActivityInscriptionBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class InscriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInscriptionBinding
     private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        binding = ActivityInscriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnInsription.setOnClickListener{
            val pseudo = binding.pseudo.text.toString()
            val passwordConfirm = binding.passwordConfirm.text.toString()
            val mail = binding.addressMail.text.toString()
            val password = binding.password.text.toString()
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
                registerUser(mail, password)

            }
        }


    }

    private fun isMailValid(mail: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()
    }
    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this,ConnectionActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(
                        baseContext, "L'authentification a échouée.",
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