package com.centennial.jovichenmcintyre_mapd711_assignment4.ui.register
//Name: Jovi Chen-Mcintyre
//ID: 301125059

import android.content.Context
import androidx.lifecycle.ViewModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.CustomerModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.repository.CustomerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {

    //insert user data to database
    fun insertCustomerData( context:Context,customerModel: CustomerModel) {
        CoroutineScope(Dispatchers.IO).launch {
            CustomerRepository.insertData(context, customerModel)
        }
    }
}