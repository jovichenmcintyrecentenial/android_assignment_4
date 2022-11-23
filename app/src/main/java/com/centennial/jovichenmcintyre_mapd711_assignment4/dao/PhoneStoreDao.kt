package com.centennial.jovichenmcintyre_mapd711_assignment4.dao
//Name: Jovi Chen-Mcintyre
//ID: 301125059

import androidx.lifecycle.LiveData
import androidx.room.*
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.CustomerModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.OrderModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.ProductModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.ProductOrder

@Dao
interface PhoneStoreDao {

    //insert customer data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCustomer(studentModel: CustomerModel)

    //check use credenttials and return custom obj on match
    @Query("SELECT * FROM customers WHERE email =:userName AND password =:password")
    fun passwordCheck(userName: String,password:String) : CustomerModel?

    //get customer based on their email
    @Query("SELECT * FROM customers WHERE email =:userName")
    fun getCustomer(userName: String) : CustomerModel?

    //update customer model
    @Update
    fun updateCustomer(customerModel:CustomerModel)

    //defining an insert method using @Insert Annotation
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(productModel: ProductModel)

    //defining an insert method using @Insert Annotation
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrder(orderModel: OrderModel)

    //get all products
    @Query("SELECT * FROM products")
    fun getAllProducts() : List<ProductModel>?

    // get order and product using join query
    @Query("SELECT orders.*, products.* FROM orders INNER JOIN products ON products.productId = orders.prodId WHERE custId =:id")
    fun getMyOrders (id:Int) : List<ProductOrder>?

    //get product by phone make and model
    @Query("SELECT * FROM products WHERE phoneMake =:phoneMake AND phoneModel =:phoneModel")
    fun getProduct (phoneMake:String, phoneModel:String) : ProductModel?

}