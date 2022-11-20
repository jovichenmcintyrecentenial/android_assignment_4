package com.centennial.jovichenmcintyre_mapd711_assignment4.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.centennial.jovichenmcintyre_mapd711_assignment4.database.PhoneStoreDatabase
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.CustomerModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomerRepository {

    //defining database and live data object as companion objects
    companion object {
        private var phoneStoreDatabase: PhoneStoreDatabase? = null
        private lateinit var customerModelLiveData: LiveData<CustomerModel?>

        //initialize database
        private fun getDB(context: Context) : PhoneStoreDatabase {
            return PhoneStoreDatabase.getDatabaseClient(context)
        }

        fun insertData(context: Context, customer:CustomerModel) {
            phoneStoreDatabase = getDB(context)

            CoroutineScope(Dispatchers.IO).launch {

                phoneStoreDatabase!!.phonestoreDao().insertCustomer(customer)
            }

        }

        fun passwordCheck(context: Context, username: String,password:String) : LiveData<CustomerModel?> {
            phoneStoreDatabase = getDB(context)
            customerModelLiveData = phoneStoreDatabase!!.phonestoreDao().passwordCheck(username, password)
            return customerModelLiveData
        }

    }
}