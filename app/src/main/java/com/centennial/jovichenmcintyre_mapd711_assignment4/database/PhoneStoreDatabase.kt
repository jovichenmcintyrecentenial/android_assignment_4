package com.centennial.jovichenmcintyre_mapd711_assignment4.database

import android.content.Context
import androidx.room.*
import com.centennial.jovichenmcintyre_mapd711_assignment4.dao.PhoneStoreDao
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.CustomerModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.OrderModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.ProductModel

//Room database
@Database(entities = arrayOf(OrderModel::class,CustomerModel::class,ProductModel::class,), version = 2, exportSchema = false)
abstract class PhoneStoreDatabase : RoomDatabase() {
    //instantiating DAO object
    abstract fun phonestoreDao() : PhoneStoreDao

    //companion object means an object declaration inside a class
    companion object {

        //Volatile object or property is immediately made visible to other threads.
        @Volatile
        private var INSTANCE: PhoneStoreDatabase? = null

        //create a database name "STOREDB"
        fun getDatabaseClient(context: Context) : PhoneStoreDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, PhoneStoreDatabase::class.java, "STOREDB")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }
    }
}