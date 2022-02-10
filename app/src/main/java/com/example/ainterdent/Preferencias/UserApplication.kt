package com.example.ainterdent.Preferencias

import android.app.Application

//esto va persistir durante todo el ciclo de vida de la applicacion
class UserApplication : Application() {

    companion object{
        lateinit var prefs:Prefs
    }
    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
}