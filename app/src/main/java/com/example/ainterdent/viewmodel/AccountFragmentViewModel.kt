package com.example.ainterdent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ainterdent.model.Producto
import com.example.ainterdent.model.User

import com.example.ainterdent.repos.EcommerceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountFragmentViewModel : ViewModel() {

    private val _update : MutableLiveData<User> = MutableLiveData()
    val updateResponse : LiveData<User>
    get() = _update

    val _user = MutableLiveData<User>()

    fun update(id: Int, user:User){
        viewModelScope.launch ( Dispatchers.Default ){
            _update.postValue(EcommerceRepository().updateUser(id,user))
        }
    }

    fun listar(id: Int){
        viewModelScope.launch  (Dispatchers.Default){
            _user.postValue(EcommerceRepository().findById(id))
        }
    }
}