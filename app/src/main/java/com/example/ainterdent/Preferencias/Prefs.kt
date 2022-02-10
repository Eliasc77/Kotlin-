package com.example.ainterdent.Preferencias

import android.content.Context

class Prefs(val context: Context) {

    val SHARED_NAME = "Mydtb"
    val SHARED_USER_EMAIL="EMAIL"
    val SHARED_PASSWD="PASSWORD"
    val SHARED_ID="ID"


    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveId(id : Int){
        storage.edit().putInt(SHARED_ID,id).apply()
    }

    fun saveEmail(email:String){
        storage.edit().putString(SHARED_USER_EMAIL, email).apply()
    }

    fun savePasswd(passwd:String){
        storage.edit().putString(SHARED_PASSWD, passwd).apply()
    }

    fun getEmail():String{
        return storage.getString(SHARED_USER_EMAIL,"")!!
    }

    fun getPasswd():String{
        return storage.getString(SHARED_PASSWD,"")!!
    }

    fun getId():Int{
        return storage.getInt(SHARED_ID,0)!!
    }

    //borra la bd
    fun wipe(){
        storage.edit().clear().apply()
    }
}