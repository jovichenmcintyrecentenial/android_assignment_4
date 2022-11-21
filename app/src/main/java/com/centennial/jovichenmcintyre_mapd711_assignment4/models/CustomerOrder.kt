package com.centennial.jovichenmcintyre_mapd711_assignment4.models

import androidx.room.Embedded


class ProductOrder {
    @Embedded
    var productModel: ProductModel? = null

    @Embedded
    var orderModel: OrderModel? = null
}