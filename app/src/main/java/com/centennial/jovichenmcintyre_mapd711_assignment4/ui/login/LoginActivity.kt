package com.centennial.jovichenmcintyre_mapd711_assignment4.ui.login

import UserInputException
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.centennial.jovichenmcintyre_mapd711_assignment4.BottomNavigationActivity
import com.centennial.jovichenmcintyre_mapd711_assignment4.R
import com.centennial.jovichenmcintyre_mapd711_assignment4.ui.register.RegisterAcitivy
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.CustomerModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.repository.ProductRepository
import com.centennial.jovichenmcintyre_mapd711_assignment4.utils.Utils

class LoginActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginViewModel = ViewModelProvider(this).get(modelClass = LoginViewModel::class.java)

        usernameEditText = findViewById(R.id.uname)
        passwordEditText = findViewById(R.id.password)

        val loginObserver = Observer<CustomerModel?> { customerModel ->

            if(customerModel != null) {
                val sharedPreference =  getSharedPreferences("STORE",Context.MODE_PRIVATE)

                var editor = sharedPreference.edit()
                editor.putString("username", customerModel.firstname)
                editor.apply()

                Toast.makeText(this,getString(R.string.login_success),Toast.LENGTH_LONG).show()
                var intent = Intent(this,BottomNavigationActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)

            }
            else{
                Toast.makeText(this,getString(R.string.invalid_pwd_or_username),Toast.LENGTH_LONG).show()
            }
        }

        loginViewModel.liveCustomerData.observe(this, loginObserver)

        ProductRepository.initialProductData(this)



    }

    fun register(view: View) {
        var intent = Intent(this, RegisterAcitivy::class.java)
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

        Utils.emptyValidation(usernameEditText, "Enter a username")
        Utils.emptyValidation(passwordEditText, "Enter a password")

        return true
    }
}