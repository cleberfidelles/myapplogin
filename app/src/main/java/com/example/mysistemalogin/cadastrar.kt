package com.example.mysistemalogin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mysistemalogin.databinding.ActivityCadastrarBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class cadastrar : AppCompatActivity() {
    private lateinit var binding : ActivityCadastrarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        voltar()
        binding = ActivityCadastrarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCadastrar.setOnClickListener(){cad->
            val email = binding.pegaemail.text.toString()
            val senha = binding.pegasenha.text.toString()
            if(email.isEmpty() || senha.isEmpty()){
                val erro = Snackbar.make(cad,"Preencha todos os campos",Snackbar.LENGTH_SHORT)
                erro.setBackgroundTint(Color.RED)
                erro.show()
            }else{
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener(){sucesso->
                    if(sucesso.isSuccessful){
                        Toast.makeText(this,"Cadastrado com sucesso",Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener(){error->
                    Toast.makeText(this,"Erro ao cadastrar",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    fun voltar(){
        binding.home.setOnClickListener() {
            val troca = Intent(this, MainActivity::class.java)
            startActivity(troca)
            finish()
        }
    }
}