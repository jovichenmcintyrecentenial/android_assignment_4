package com.centennial.jovichenmcintyre_mapd711_assignment4.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
data class ProductModel(
    //defining a column phoneMake
    @ColumnInfo(name = "phoneMake")
    var phoneMake: String,
    //defining a column phoneModel
    @ColumnInfo(name = "phoneModel")
    var phoneModel: String,
    //defining a column phoneColor
    @ColumnInfo(name = "phoneColor")
    var phoneColor: String,
    //defining a column storageCapacity
    @ColumnInfo(name = "storageCapacity")
    var storageCapacity: String,
    //defining a column price
    @ColumnInfo(name = "price")
    var price: String,

)
{
    //defining a primary key field Id
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "productId")
    var id: Int? = null
}
