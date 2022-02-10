package com.example.ainterdent.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ainterdent.model.Producto
import com.example.ainterdent.repos.EcommerceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailProductActivityViewModel : ViewModel() {

    val productDetails = MutableLiveData<Producto>()

    fun fetchProductDetails(id: Int){
        viewModelScope.launch(Dispatchers.Default){
            productDetails.postValue(EcommerceRepository().fetchAllProducts().first{it.codPro == id})
        }
    }
}