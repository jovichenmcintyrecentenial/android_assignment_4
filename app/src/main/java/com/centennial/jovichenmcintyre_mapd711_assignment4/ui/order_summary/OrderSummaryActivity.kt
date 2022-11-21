package com.centennial.jovichenmcintyre_mapd711_assignment4.ui.order_summary
//Name: Jovi Chen-Mcintyre
//ID: 301125059
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.centennial.jovichenmcintyre_mapd711_assignment4.R
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.OrderModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.PhoneCheckOut
import com.centennial.jovichenmcintyre_mapd711_assignment4.ui.udpate_customer.UpdateCustumerViewModel
import com.google.gson.Gson

class OrderSummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)

        val orderViewModel = ViewModelProvider(this).get(modelClass = OrderViewModel::class.java)


        //update title
        supportActionBar?.title = getString(R.string.order_summary)

        //find and store views
        val phoneImage = findViewById<ImageView>(R.id.phone_image)
        val companyName = findViewById<TextView>(R.id.company)
        val modelName = findViewById<TextView>(R.id.model)
        val phoneColor = findViewById<TextView>(R.id.phone_color)
        val internalStorage = findViewById<TextView>(R.id.internal_storage)
        val cardType = findViewById<TextView>(R.id.card_type)
        val last4Digits = findViewById<TextView>(R.id.last_4_digits)
        val price = findViewById<TextView>(R.id.price)
        
        val userName = findViewById<TextView>(R.id.user_name)
        val address = findViewById<TextView>(R.id.address)
        val city = findViewById<TextView>(R.id.city)
        val postalCode = findViewById<TextView>(R.id.postal_code)

        //deserialize data from intent
        val checkoutObj = Gson().fromJson(intent.getStringExtra("checkout"), PhoneCheckOut::class.java)

        //display phone image
        val resourceImage: Int = resources.getIdentifier(checkoutObj.phone.imageUri, "drawable", packageName)
        phoneImage?.setImageResource(resourceImage)

        //populate views
        companyName.text = checkoutObj.phone.phoneMake
        modelName.text = checkoutObj.phone.phoneModel
        phoneColor.text = checkoutObj.phone.phoneColor
        internalStorage.text = checkoutObj.phone.storageCapacity
        cardType.text = checkoutObj.cardType.toString()
        //show last 4 digits
        last4Digits.text = checkoutObj.cardNumber?.substring(12)
        price.text = checkoutObj.phone.getFormatterPrice()
        //populate views
        userName.text = checkoutObj.firstName+" "+checkoutObj.lastName
        address.text = checkoutObj.address
        city.text = checkoutObj.city
        postalCode.text = checkoutObj.postalCode

        val updateViewModel = ViewModelProvider(this).get(modelClass = UpdateCustumerViewModel::class.java)


        updateViewModel.liveCustomerData.observe(this, androidx.lifecycle.Observer {
            if(it != null){
                val unixTime = System.currentTimeMillis() / 1000L
                var order = OrderModel(it.id!!, checkoutObj.phone.id!!, "Ordered",unixTime)
                orderViewModel.addOrder(this,order)
            }
        })

        updateViewModel.getCustomer(this)


//        orderViewModel.addOrder(this,)
        
    }
}