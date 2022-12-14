package com.centennial.jovichenmcintyre_mapd711_assignment4.models
//Name: Jovi Chen-Mcintyre
//ID: 301125059

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class CustomerModel(
    //defining a column orderId
    @ColumnInfo(name = "email")
    var email: String,
    //defining a column firstname
    @ColumnInfo(name = "firstname")
    var firstname: String,
    //defining a column status
    @ColumnInfo(name = "lastname")
    var lastname: String,
    //defining a column orderDate
    @ColumnInfo(name = "address")
    var address: String,
    @ColumnInfo(name = "city")
    var city: String,
    @ColumnInfo(name = "postal")
    var postal: String,
    //defining a column password
    @ColumnInfo(name = "password")
    var password: String,
)
{
    //defining a primary key field Id
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "custId")
    var id: Int? = null
}

