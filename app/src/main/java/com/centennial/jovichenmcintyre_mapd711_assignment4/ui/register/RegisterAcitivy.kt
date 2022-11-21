package com.centennial.jovichenmcintyre_mapd711_assignment4.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.centennial.jovichenmcintyre_mapd711_001_assignment2.exceptions.UserInputException
import com.centennial.jovichenmcintyre_mapd711_assignment4.R
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.CustomerModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.utils.Utils

class RegisterAcitivy : AppCompatActivity() {

    lateinit var registerViewModel: RegisterViewModel

    private lateinit var username: EditText
    private lateinit var  firstname: EditText
    private lateinit var  lastname: EditText
    private lateinit var  address: EditText
    private lateinit var  city: EditText
    private lateinit var  postalCode: EditText
    private lateinit var  password: EditText
    private lateinit var  rePassword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_acitivy)

        username = findViewById(R.id.uname)
        firstname = findViewById(R.id.firstname)
        lastname = findViewById(R.id.lastname)
        address = findViewById(R.id.address)
        city = findViewById(R.id.city)
        postalCode = findViewById(R.id.postal)
        password = findViewById(R.id.password1)
        rePassword = findViewById(R.id.password2)

        registerViewModel = ViewModelProvider(this).get(modelClass = RegisterViewModel::class.java)


    }

    //validate edit text information if there is an issue throw an exception
    private fun isDataValid(): Boolean {
        Utils.emptyValidation(username,"Please enter a username")
        Utils.emptyValidation(firstname,"Please enter a firstname")
        Utils.emptyValidation(lastname,"Please enter a lastname")
        Utils.emptyValidation(address,"Please enter a address")
        Utils.emptyValidation(city,"Please enter a city")
        Utils.emptyValidation(postalCode,"Please enter a postalCode")
        Utils.emptyValidation(password,"Please enter a password")
        Utils.emptyValidation(rePassword,"Please re-enter password")

        if(rePassword.text.toString() != password.text.toString()){
            throw UserInputException("Password doesn't match, please try again.")
        }

        return true
    }



    fun onSubmit(view: View) {
        try{
            if(isDataValid()){

                val username = this.username.text.toString()
                val firstname = this.firstname.text.toString()
                val lastname = this.lastname.text.toString()
                val address = this.address.text.toString()
                val city = this.city.text.toString()
                val postalCode = this.postalCode.text.toString()
                val password = this.password.text.toString()

                val customerModel = CustomerModel(username,firstname,lastname,address,city,postalCode,password)
                registerViewModel.insertCustomerData(this,customerModel)
                Toast.makeText(this,"Registration successful",Toast.LENGTH_LONG).show()
                finish()
//                val newIntent = Intent(this,ConfirmationCheckOutActivity::class.java)
//                //serial checkout object save to intent
//                newIntent.putExtra("checkout" , Gson().toJson(checkoutObj))
//                startActivity(newIntent)

            }
        }
        //catch  and display user input exception
        catch (e: UserInputException) {
            //display exception message
            Utils.showMessage(this,e.message.toString())
        }
    }

}