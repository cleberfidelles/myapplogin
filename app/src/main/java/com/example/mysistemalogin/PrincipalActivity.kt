package com.example.mysistemalogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mysistemalogin.databinding.ActivityCadastrarBinding
import com.example.mysistemalogin.databinding.PrincipalBinding
import com.google.firebase.auth.FirebaseAuth

class PrincipalActivity : AppCompatActivity() {
    private lateinit var binding : PrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.deslogar.setOnClickListener(){
            FirebaseAuth.getInstance().signOut()
            val troca = Intent(this,MainActivity::class.java)
            startActivity(troca)
            finish()
        }
    }
}