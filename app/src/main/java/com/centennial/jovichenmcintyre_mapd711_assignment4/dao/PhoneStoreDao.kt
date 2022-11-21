package com.centennial.jovichenmcintyre_mapd711_assignment4.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.CustomerModel
@Dao
interface PhoneStoreDao {

    //defining an insert method using @Insert Annotation
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCustomer(studentModel: CustomerModel)

    @Query("SELECT * FROM customers WHERE userName =:userName AND password =:password")
    fun passwordCheck(userName: String,password:String) : CustomerModel?

    @Update
    fun updateCustomer(customerModel:CustomerModel)
//
//    //defining a query method using @Query Annotation
//    @Query("SELECT * FROM student WHERE StudentName =:studentname")
//    fun getCustomers(studentname: String?) : LiveData<CustomerModel>
}