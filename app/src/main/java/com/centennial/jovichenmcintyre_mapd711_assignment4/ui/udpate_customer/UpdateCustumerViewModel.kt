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

    //live data for customer
    val liveCustomerData: MutableLiveData<CustomerModel?> by lazy {
        MutableLiveData<CustomerModel?>()
    }

    //get and update live data for customer
    fun getCustomer( context:Context) {
        //if custome data avavible in CustomerRepository just take the value of that varible
        if(CustomerRepository.loginCustomer.value != null) {
            liveCustomerData.value = CustomerRepository.loginCustomer.value
        }
        else{
            //if fail to get use from repo then use user name to search for customer information
            val sharedPreference =  context.getSharedPreferences("STORE",Context.MODE_PRIVATE)
            var username = sharedPreference.getString("username","")
            if(username != null){
                CoroutineScope(Dispatchers.IO).launch {
                    liveCustomerData.postValue(CustomerRepository.getData(context, username))
                }
            }

        }
    }

    //update customer data
    fun updateCustomer( context:Context) {
        CoroutineScope(Dispatchers.IO).launch {
            CustomerRepository.update(context, liveCustomerData.value!!)
        }
    }
}