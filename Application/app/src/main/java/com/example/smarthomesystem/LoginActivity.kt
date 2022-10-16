package com.example.smarthomesystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.smarthomesystem.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(loginBinding.root)

        loginBinding.loginButton.setOnClickListener {
            login()
        }
    }


    fun login() {
        var userId = loginBinding.idEdittext.text.toString()
        var userPassword = loginBinding.passwordEdittext.text.toString()

        Log.e("Login", "userId : " + userId.toString())
        Log.e("Login", "userId : " + userPassword.toString())
    }
}