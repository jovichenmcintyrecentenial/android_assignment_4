package com.centennial.jovichenmcintyre_mapd711_assignment4.repository
//Name: Jovi Chen-Mcintyre
//ID: 301125059

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.centennial.jovichenmcintyre_mapd711_assignment4.database.PhoneStoreDatabase
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.CustomerModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomerRepository {

    //defining database and live data object as companion objects
    companion object {
        private var phoneStoreDatabase: PhoneStoreDatabase? = null
        val loginCustomer: MutableLiveData<CustomerModel?> by lazy {
            MutableLiveData<CustomerModel?>()
        }

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

        fun getData(context: Context, username:String):CustomerModel? {
            phoneStoreDatabase = getDB(context)

            var customer = phoneStoreDatabase!!.phonestoreDao().getCustomer(username)
            loginCustomer.postValue(customer)

            return customer
        }

        fun update(context: Context, customer:CustomerModel) {
            phoneStoreDatabase = getDB(context)

            CoroutineScope(Dispatchers.IO).launch {

                phoneStoreDatabase!!.phonestoreDao().updateCustomer(customer)
            }

        }

        fun passwordCheck(context: Context, username: String, password:String):CustomerModel? {
            phoneStoreDatabase = getDB(context)

            var customer = phoneStoreDatabase!!.phonestoreDao().passwordCheck(username, password)
            loginCustomer.postValue(customer)

            return customer
        }

    }
}