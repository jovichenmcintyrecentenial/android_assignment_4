package com.centennial.jovichenmcintyre_mapd711_assignment4.ui.udpate_customer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.centennial.jovichenmcintyre_mapd711_assignment4.R
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.CustomerModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.ui.register.RegisterViewModel

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

        findViewById<TextView>(R.id.uname_label).visibility = View.GONE
        findViewById<TextView>(R.id.fname_label).visibility = View.GONE
        findViewById<TextView>(R.id.lname_label).visibility = View.GONE
//        findViewById<TextView>(R.id.password1_label).visibility = View.GONE
//        findViewById<TextView>(R.id.password2_label).visibility = View.GONE
        username.visibility = View.GONE
        firstname.visibility = View.GONE
        lastname.visibility = View.GONE
//        password.visibility = View.GONE
//        rePassword.visibility = View.GONE


        updateViewModel = ViewModelProvider(this).get(modelClass = UpdateCustumerViewModel::class.java)

        updateViewModel.liveCustomerData.observe(this, Observer {

            if(it != null) {
                username.setText(it.userName)
                city.setText(it.city)
                address.setText(it.address)
                postalCode.setText(it.postal)
            }

        })

        updateViewModel.getCustomer(this)


    }
}