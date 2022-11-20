package com.centennial.jovichenmcintyre_mapd711_assignment4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.centennial.jovichenmcintyre_mapd711_001_assignment2.exceptions.UserInputException
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.CustomerModel
import java.util.*

class RegisterAcitivy : AppCompatActivity() {

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



    }

     private fun _isEmptyValidation(edittext:EditText,error:String):Boolean {
         if(edittext.text.trim().isEmpty()) {
             throw UserInputException(error)
         }
         return  false
    }


    //validate edit text information if there is an issue throw an exception
    private fun isDataValid(): Boolean {
        _isEmptyValidation(username,"Please enter a username")
        _isEmptyValidation(firstname,"Please enter a firstname")
        _isEmptyValidation(lastname,"Please enter a lastname")
        _isEmptyValidation(address,"Please enter a address")
        _isEmptyValidation(city,"Please enter a city")
        _isEmptyValidation(postalCode,"Please enter a postalCode")
        _isEmptyValidation(password,"Please enter a password")
        _isEmptyValidation(rePassword,"Please re-enter password")

        if(rePassword != password){
            throw UserInputException("Password doesn't match, please try again.")
        }

        return true
    }

    //use to display toast messages
    private fun showMessage(message:String){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }


    fun onSubmit(view: View) {
        try{
            if(isDataValid()){

                var username = this.username.text.toString()
                var firstname = this.firstname.text.toString()
                var lastname = this.lastname.text.toString()
                var address = this.address.text.toString()
                var city = this.city.text.toString()
                var postalCode = this.postalCode.text.toString()
                var password = this.password.text.toString()

                var customerModel = CustomerModel(username,firstname,lastname,address,city,postalCode,password)

//                val newIntent = Intent(this,ConfirmationCheckOutActivity::class.java)
//                //serial checkout object save to intent
//                newIntent.putExtra("checkout" , Gson().toJson(checkoutObj))
//                startActivity(newIntent)

            }
        }
        //catch  and display user input exception
        catch (e: UserInputException) {
            //display exception message
            showMessage(e.message.toString())
        }
    }

}