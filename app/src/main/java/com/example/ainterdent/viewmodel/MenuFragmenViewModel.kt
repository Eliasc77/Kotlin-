package com.example.ainterdent.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ainterdent.model.Categoria
import com.example.ainterdent.model.Producto
import com.example.ainterdent.repos.EcommerceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuFragmenViewModel : ViewModel() {

    val categorias = MutableLiveData<List<Categoria>>()
    val productos = MutableLiveData<List<Producto>>()

    fun loadCategory(){
        viewModelScope.launch(Dispatchers.Default){
            categorias.postValue(EcommerceRepository().fetchAllCategoriesRetrofit())
        }
    }


    fun loadProducts(){
        viewModelScope.launch(Dispatchers.Default){
            productos.postValue(EcommerceRepository().fetchProductPopular())
        }
    }

}