package com.example.ainterdent.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ainterdent.Preferencias.UserApplication
import com.example.ainterdent.R
import com.example.ainterdent.model.AuthenticateResponse
import com.example.ainterdent.model.RequestUser
import com.example.ainterdent.viewmodel.LoginActivityViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    lateinit var viewModel: LoginActivityViewModel

    //google


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)
        viewModel.passwordResponse.observe(this, Observer {

                    if(it.user.email!= null && it.user.password != null){
                        UserApplication.prefs.saveId(it.user.codUsu)
                        UserApplication .prefs.saveEmail(it.user.email)
                        UserApplication.prefs.savePasswd(it.user.password)
                        goToActivityMenu()
                        Toast.makeText(this, "Bienvenido Usuario ${it.user.email}", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(applicationContext, "Yno", Toast.LENGTH_SHORT).show()
                    }
        })



        initUI()
        checkUserValues()

    }


    fun checkUserValues(){
        if(UserApplication.prefs.getEmail().isNotEmpty() && UserApplication.prefs.getPasswd().isNotEmpty()){
            goToActivityMenu()
        }
    }

    private fun goToActivityMenu() {
        val intent = Intent(applicationContext, MenuActivity::class.java)
        startActivity(intent)
    }


    fun initUI(){
        btnLogin.setOnClickListener{accesToApplication()}
        etRegister.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
        }
    }

    private fun accesToApplication() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val request = RequestUser(
            email,
            password
        )
        if(!validar()){
            return
        }else{
            viewModel.request(request)
        }
    }

    fun validar():Boolean{
        var response = true
        val email= etEmail.text.toString()
        val pass = etPassword.text.toString()

        if(email.isEmpty()){
            etEmail.setError("Ingrese su email")
            response = false
        }
        if(pass.isEmpty()){
            etPassword.setError("Ingrese su password")
            response = false
        }
        return response
    }


}