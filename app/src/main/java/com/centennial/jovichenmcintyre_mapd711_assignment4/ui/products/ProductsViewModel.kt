package com.centennial.jovichenmcintyre_mapd711_assignment4.ui.products
//Name: Jovi Chen-Mcintyre
//ID: 301125059

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.ProductModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {

    //live data for list of products
    val listOfProductLiveData: MutableLiveData<List<ProductModel>?> by lazy {
        MutableLiveData<List<ProductModel>?>()
    }

    //get list of products and update live data valuse
    fun getProducts(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            listOfProductLiveData.postValue(ProductRepository.getAllProducts(context))
        }
    }


}