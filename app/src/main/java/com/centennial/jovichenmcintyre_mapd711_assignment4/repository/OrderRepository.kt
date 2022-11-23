package com.centennial.jovichenmcintyre_mapd711_assignment4.repository
//Name: Jovi Chen-Mcintyre
//ID: 301125059

import android.content.Context
import com.centennial.jovichenmcintyre_mapd711_assignment4.database.PhoneStoreDatabase
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.OrderModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.ProductModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.ProductOrder
import com.centennial.jovichenmcintyre_mapd711_assignment4.repository.OrderRepository.Companion.phoneStoreDatabase
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
        //function used to update and insert order data
        fun insertUpdateData(context: Context, orderModel: OrderModel) {
            phoneStoreDatabase = getDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                phoneStoreDatabase!!.phonestoreDao().insertOrder(orderModel)
            }

        }
        //get all order from customer with specified id
        fun getMyProductsOrders(context: Context, id: Int): List<ProductOrder>? {

            phoneStoreDatabase = getDB(context)

            return phoneStoreDatabase!!.phonestoreDao().getMyOrders(id)

        }


    }
}