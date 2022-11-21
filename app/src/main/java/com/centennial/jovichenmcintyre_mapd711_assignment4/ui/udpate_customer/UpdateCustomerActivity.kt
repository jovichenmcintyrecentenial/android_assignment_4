package com.centennial.jovichenmcintyre_mapd711_assignment4.ui.udpate_customer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.centennial.jovichenmcintyre_mapd711_001_assignment2.exceptions.UserInputException
import com.centennial.jovichenmcintyre_mapd711_assignment4.R
import com.centennial.jovichenmcintyre_mapd711_assignment4.utils.Utils

class UpdateCustomerActivity : AppCompatActivity() {

    lateinit var updateViewModel: UpdateCustumerViewModel


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
        var button = findViewById<Button>(R.id.register)

        findViewById<TextView>(R.id.uname_label).visibility = View.GONE
        findViewById<TextView>(R.id.fname_label).visibility = View.GONE
        findViewById<TextView>(R.id.lname_label).visibility = View.GONE
        username.visibility = View.GONE
        firstname.visibility = View.GONE
        lastname.visibility = View.GONE

        button.text = "Update"

        updateViewModel = ViewModelProvider(this).get(modelClass = UpdateCustumerViewModel::class.java)

        updateViewModel.liveCustomerData.observe(this, Observer {

            if(it != null) {
                username.setText(it.email)
                city.setText(it.city)
                address.setText(it.address)
                postalCode.setText(it.postal)
            }

        })

        updateViewModel.getCustomer(this)

        button.setOnClickListener {
            onSubmit()
        }

    }

    private fun onSubmit() {
        try{
            if(isDataValid()){

                val address = this.address.text.toString()
                val city = this.city.text.toString()
                val postalCode = this.postalCode.text.toString()
                val password = this.password.text.toString()

                updateViewModel.liveCustomerData.value!!.address = address
                updateViewModel.liveCustomerData.value!!.city = city
                updateViewModel.liveCustomerData.value!!.postal = postalCode
                updateViewModel.liveCustomerData.value!!.city = city

                if(rePassword.text.toString().isNotEmpty() || this.password.text.toString().isNotEmpty() ) {
                    updateViewModel.liveCustomerData.value!!.password = password
                }

                updateViewModel.updateCustomer(this)

                Toast.makeText(this,"Update successful", Toast.LENGTH_LONG).show()
                finish()


            }
        }
        //catch  and display user input exception
        catch (e: UserInputException) {
            //display exception message
            Utils.showMessage(this,e.message.toString())
        }
    }

    //validate edit text information if there is an issue throw an exception
    private fun isDataValid(): Boolean {
        Utils.emptyValidation(address,"Please enter a address")
        Utils.emptyValidation(city,"Please enter a city")
        Utils.emptyValidation(postalCode,"Please enter a postalCode")

        if(rePassword.text.toString().isNotEmpty() || password.text.toString().isNotEmpty() ) {

            Utils.emptyValidation(password,"Please enter a password")
            Utils.emptyValidation(rePassword,"Please re-enter password")

            if (rePassword.text.toString() != password.text.toString()) {
                throw UserInputException("Password doesn't match, please try again.")
            }
        }

        return true
    }
}