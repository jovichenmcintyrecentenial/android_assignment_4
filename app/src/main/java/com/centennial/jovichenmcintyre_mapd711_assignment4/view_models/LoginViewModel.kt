package com.centennial.jovichenmcintyre_mapd711_assignment4.view_models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.database.PhoneStoreDatabase
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.CustomerModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.repository.CustomerRepository

class LoginViewModel: ViewModel() {

    lateinit var liveCustomerData: LiveData<CustomerModel?>


    fun getUser(context: Context, username:String, password:String){
        liveCustomerData = CustomerRepository.passwordCheck(context,username,password)
    }


}