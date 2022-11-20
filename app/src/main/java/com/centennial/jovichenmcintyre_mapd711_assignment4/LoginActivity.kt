package com.centennial.jovichenmcintyre_mapd711_assignment4

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.CustomerModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.view_models.LoginViewModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.view_models.RegisterViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginViewModel = ViewModelProvider(this).get(modelClass = LoginViewModel::class.java)




    }

    fun register(view: View) {
        var intent = Intent(this,RegisterAcitivy::class.java)
        startActivity(intent)
    }

    fun onLogin(view: View) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)

        val randomObserver = Observer<CustomerModel?> { customerModel ->
            var editor = sharedPref.edit()
            editor.putString("username",customerModel.firstname)
            editor.commit()


        }

        loginViewModel.liveCustomerData.observe(this, randomObserver)


    }
}