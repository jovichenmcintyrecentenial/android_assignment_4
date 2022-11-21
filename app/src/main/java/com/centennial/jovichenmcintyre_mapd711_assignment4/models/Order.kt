package com.centennial.jovichenmcintyre_mapd711_assignment4.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderModel(
    //defining a column custId
    @ColumnInfo(name = "custId")
    var custId: Int,
    //defining a column productId
    @ColumnInfo(name = "productId")
    var productId: Int,
    //defining a column status
    @ColumnInfo(name = "status")
    var status: String,
    //defining a column orderDate
    @ColumnInfo(name = "orderDate")
    var orderDate: Long

)
{
    //defining a primary key field Id
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "orderId")
    var id: Int? = null
}