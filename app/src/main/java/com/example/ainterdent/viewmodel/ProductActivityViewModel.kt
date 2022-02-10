package com.example.ainterdent.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ainterdent.model.Producto
import com.example.ainterdent.repos.EcommerceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductActivityViewModel : ViewModel() {

    val products = MutableLiveData<List<Producto>>()

    fun loadProducts(){
        viewModelScope.launch(Dispatchers.Default){
            products.postValue(EcommerceRepository().fetchAllProducts())
        }
    }

    fun search(term : String){
        viewModelScope.launch (Dispatchers.Default){
            products.postValue(EcommerceRepository().fetchSearchProduct(term))
        }
    }




}