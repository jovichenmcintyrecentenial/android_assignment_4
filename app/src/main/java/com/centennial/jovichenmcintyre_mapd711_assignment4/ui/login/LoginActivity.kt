package com.centennial.jovichenmcintyre_mapd711_assignment4.ui.login

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
                Toast.makeText(this,getString(R.string.login_success),Toast.LENGTH_LONG).show()
                startActivity(Intent(this,BottomNavigationActivity::class.java))
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