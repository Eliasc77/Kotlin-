package com.example.ainterdent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ainterdent.model.AuthenticateResponse
import com.example.ainterdent.model.RequestUser
import com.example.ainterdent.repos.EcommerceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginActivityViewModel : ViewModel() {
    private val _respo : MutableLiveData<AuthenticateResponse>  = MutableLiveData()
        val passwordResponse : LiveData<AuthenticateResponse>
        get()=_respo

    fun request(request : RequestUser){
        viewModelScope.launch(Dispatchers.Default){
            _respo.postValue(EcommerceRepository().fetchlogin(request))
        }
    }
}