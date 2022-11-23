package com.centennial.jovichenmcintyre_mapd711_assignment4.ui.order_summary
//Name: Jovi Chen-Mcintyre
//ID: 301125059

import android.content.Context
import androidx.lifecycle.ViewModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.OrderModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.repository.OrderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderViewModel: ViewModel() {
    //function to add order model to database
    fun addOrder( context:Context,orderModel: OrderModel) {
        CoroutineScope(Dispatchers.IO).launch {
            OrderRepository.insertUpdateData(context, orderModel)
        }
    }
    //function to update order in database
    fun updateOrder( context:Context,orderModel: OrderModel) {
        CoroutineScope(Dispatchers.IO).launch {
            OrderRepository.insertUpdateData(context, orderModel)
        }
    }
}