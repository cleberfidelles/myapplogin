package com.example.mysistemalogin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mysistemalogin.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cadastrar()
        binding.btnEntrar.setOnClickListener{autenticacao->
            val email = binding.pegaemail.text.toString()
            val senha = binding.pegasenha.text.toString()
            if(email.isEmpty() || senha.isEmpty()){
                val erro = Snackbar.make(autenticacao,"Preencha todos os dados",Snackbar.LENGTH_SHORT)
                erro.setBackgroundTint(Color.RED)
                erro.show()
            }else{
                auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(){sucesso->
                    if(sucesso.isSuccessful){
                        principal()
                    }
                }.addOnFailureListener(){error->
                    Toast.makeText(this,"Senha ou email inválidos",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    fun cadastrar(){
        binding.cadastro.setOnClickListener(){
            val trocaTela = Intent(this, cadastrar::class.java)
            startActivity(trocaTela)
            finish()
        }
    }
    fun principal(){
        val trocaPrincipal = Intent(this,PrincipalActivity::class.java)
        startActivity(trocaPrincipal)
        finish()
    }
}