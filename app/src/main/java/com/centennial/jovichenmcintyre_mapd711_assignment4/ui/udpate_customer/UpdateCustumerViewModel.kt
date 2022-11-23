package com.centennial.jovichenmcintyre_mapd711_assignment4.ui.udpate_customer
//Name: Jovi Chen-Mcintyre
//ID: 301125059

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.CustomerModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.repository.CustomerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateCustumerViewModel: ViewModel() {

    val liveCustomerData: MutableLiveData<CustomerModel?> by lazy {
        MutableLiveData<CustomerModel?>()
    }

    fun getCustomer( context:Context) {
        if(CustomerRepository.loginCustomer.value != null) {
            liveCustomerData.value = CustomerRepository.loginCustomer.value
        }
        else{
            val sharedPreference =  context.getSharedPreferences("STORE",Context.MODE_PRIVATE)
            var username = sharedPreference.getString("username","")
            if(username != null){
                CoroutineScope(Dispatchers.IO).launch {
                    liveCustomerData.postValue(CustomerRepository.getData(context, username))
                }
            }

        }
    }

    fun updateCustomer( context:Context) {
        CoroutineScope(Dispatchers.IO).launch {
            CustomerRepository.update(context, liveCustomerData.value!!)
        }
    }
}