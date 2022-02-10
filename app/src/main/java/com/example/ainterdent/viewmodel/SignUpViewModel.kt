package com.example.ainterdent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ainterdent.model.User
import com.example.ainterdent.repos.EcommerceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val _register : MutableLiveData<User> = MutableLiveData()
    val registerResponse : LiveData<User>
        get()=_register

    fun register(user : User){
        viewModelScope.launch(Dispatchers.Default){
            _register.postValue(EcommerceRepository().registerUser(user))
        }
    }
}