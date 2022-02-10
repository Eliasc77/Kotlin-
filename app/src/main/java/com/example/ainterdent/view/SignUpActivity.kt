package com.example.ainterdent.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ainterdent.Preferencias.UserApplication
import com.example.ainterdent.R
import com.example.ainterdent.databinding.ActivitySignUpBinding
import com.example.ainterdent.model.RequestUser
import com.example.ainterdent.model.User
import com.example.ainterdent.viewmodel.SignUpViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class SignUpActivity : AppCompatActivity() {


    lateinit var viewModel : SignUpViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        viewModel.registerResponse.observe(this, Observer{
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "${it}", Toast.LENGTH_LONG).show()

        })

        initUI()
        checkUserValues()
    }

    fun initUI(){
        btnRegister.setOnClickListener{sendDataToRegister()}
        etLogin.setOnClickListener{
            startActivity(Intent(applicationContext,  LoginActivity::class.java))
        }
    }
    private fun goToActivityMenu() {
        val intent = Intent(applicationContext, MenuActivity::class.java)
        startActivity(intent)
    }

    fun checkUserValues(){
        if(UserApplication.prefs.getEmail().isNotEmpty() && UserApplication.prefs.getPasswd().isNotEmpty()){
            goToActivityMenu()
        }
    }

    private fun sendDataToRegister() {
        val email = etEmail2.text.toString().trim()
        val password = etPassword2.text.toString().trim()
        val user = User(
            0,
            null,
            null,
            null,
            null,
            email,
            null,
            password,
            null,
            null,
            null
        )
        if(!validar()){
            return
        }else{
            viewModel.register(user)
        }
    }

    fun validar():Boolean{
        var response = true
        val email = etEmail2.text.toString()
        val password = etPassword2.text.toString()

        if(email.isEmpty()){
            etEmail2.setError("Ingrese su email")
            response = false
        }
        if(password.isEmpty()){
             etPassword2.setError("Ingrese su password")
             response = false
        }
        return response
    }

}