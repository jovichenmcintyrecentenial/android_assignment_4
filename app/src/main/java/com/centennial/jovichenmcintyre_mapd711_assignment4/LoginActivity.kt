package com.centennial.jovichenmcintyre_mapd711_assignment4

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.centennial.jovichenmcintyre_mapd711_001_assignment2.exceptions.UserInputException
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.CustomerModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.utils.Utils
import com.centennial.jovichenmcintyre_mapd711_assignment4.view_models.LoginViewModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.view_models.RegisterViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginViewModel = ViewModelProvider(this).get(modelClass = LoginViewModel::class.java)

        usernameEditText = findViewById(R.id.uname)
        passwordEditText = findViewById(R.id.password)

        val loginObserver = Observer<CustomerModel?> { customerModel ->

            if(customerModel != null) {
                val sharedPref = getPreferences(Context.MODE_PRIVATE)

                var editor = sharedPref.edit()
                editor.putString("username", customerModel.firstname)
                editor.commit()
                Toast.makeText(this,"Login Successful",Toast.LENGTH_LONG).show()

            }
            else{
                Toast.makeText(this,"Invalid, username or password.",Toast.LENGTH_LONG).show()
            }
        }

        loginViewModel.liveCustomerData.observe(this, loginObserver)


    }

    fun register(view: View) {
        var intent = Intent(this,RegisterAcitivy::class.java)
        startActivity(intent)
    }

    fun onLogin(view: View) {
        try{
            if(isDataValid()) {
                loginViewModel.getUser(
                    this,
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
        //catch  and display user input exception
        catch (e: UserInputException) {
            //display exception message
            Utils.showMessage(this,e.message.toString())
        }
    }

    private fun isDataValid(): Boolean {

        Utils._isEmptyValidation(usernameEditText, "Enter a username")
        Utils._isEmptyValidation(passwordEditText, "Enter a password")

        return true
    }
}