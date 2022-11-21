package com.centennial.jovichenmcintyre_mapd711_assignment4.ui.orders

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.CustomerModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.ProductOrder
import com.centennial.jovichenmcintyre_mapd711_assignment4.repository.CustomerRepository
import com.centennial.jovichenmcintyre_mapd711_assignment4.repository.OrderRepository
import com.centennial.jovichenmcintyre_mapd711_assignment4.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrdersViewModel : ViewModel() {

    val liveCustomerData: MutableLiveData<CustomerModel?> by lazy {
        MutableLiveData<CustomerModel?>()
    }
    val listOfOrdersLiveData: MutableLiveData<List<ProductOrder>?> by lazy {
        MutableLiveData<List<ProductOrder>?>()
    }

    fun getCustomer( context: Context) {
        if(CustomerRepository.loginCustomer.value != null) {
            liveCustomerData.value = CustomerRepository.loginCustomer.value
        }

    }
    fun getOrders( context: Context,id:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            listOfOrdersLiveData.postValue(OrderRepository.getMyProductsOrders(context,id))
        }
    }
}