package com.centennial.jovichenmcintyre_mapd711_assignment4.view_models

import android.content.Context
import androidx.lifecycle.ViewModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.CustomerModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.repository.CustomerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {
    fun insertCustomerData( context:Context,customerModel: CustomerModel) {
        CoroutineScope(Dispatchers.IO).launch {
            CustomerRepository.insertData(context, customerModel)
        }
    }
}