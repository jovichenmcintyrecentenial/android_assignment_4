package com.centennial.jovichenmcintyre_mapd711_assignment4.models
//Name: Jovi Chen-Mcintyre
//ID: 301125059

import androidx.room.Embedded


class ProductOrder {
    @Embedded
    var productModel: ProductModel? = null

    @Embedded
    var orderModel: OrderModel? = null
}