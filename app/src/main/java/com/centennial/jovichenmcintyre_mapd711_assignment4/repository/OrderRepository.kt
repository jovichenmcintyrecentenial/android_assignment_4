package com.centennial.jovichenmcintyre_mapd711_assignment4.repository

import android.content.Context
import com.centennial.jovichenmcintyre_mapd711_assignment4.database.PhoneStoreDatabase
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.OrderModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderRepository {

    //defining database and live data object as companion objects
    companion object {
        private var phoneStoreDatabase: PhoneStoreDatabase? = null

        //initialize database
        private fun getDB(context: Context) : PhoneStoreDatabase {
            return PhoneStoreDatabase.getDatabaseClient(context)
        }

        fun insertUpdateData(context: Context, orderModel: OrderModel) {
            phoneStoreDatabase = getDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                phoneStoreDatabase!!.phonestoreDao().insertOrder(orderModel)
            }

        }

    }
}