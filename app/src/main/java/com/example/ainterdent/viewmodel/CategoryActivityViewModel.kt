package com.example.ainterdent.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ainterdent.model.Producto
import com.example.ainterdent.repos.EcommerceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryActivityViewModel : ViewModel() {

    val products = MutableLiveData<List<Producto>>()

    fun listProductsByCategory(categoriaId : String){
        viewModelScope.launch  (Dispatchers.Default){
            products.postValue(EcommerceRepository().fetchProductByCategoria(categoriaId))
        }
    }
}