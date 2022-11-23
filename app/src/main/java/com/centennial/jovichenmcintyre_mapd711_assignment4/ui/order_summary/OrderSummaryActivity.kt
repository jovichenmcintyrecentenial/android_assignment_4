package com.centennial.jovichenmcintyre_mapd711_assignment4.ui.order_summary
//Name: Jovi Chen-Mcintyre
//ID: 301125059
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.centennial.jovichenmcintyre_mapd711_assignment4.BottomNavigationActivity
import com.centennial.jovichenmcintyre_mapd711_assignment4.R
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.OrderModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.PhoneCheckOut
import com.centennial.jovichenmcintyre_mapd711_assignment4.ui.udpate_customer.UpdateCustumerViewModel
import com.google.gson.Gson
import java.util.*

class OrderSummaryActivity : AppCompatActivity() {

    private lateinit var checkoutObj:PhoneCheckOut
    private lateinit var orderViewModel:OrderViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)

        orderViewModel = ViewModelProvider(this).get(modelClass = OrderViewModel::class.java)

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
        checkoutObj = Gson().fromJson(intent.getStringExtra("checkout"), PhoneCheckOut::class.java)

        //display phone image
        val resourceImage: Int = resources.getIdentifier(checkoutObj.phone.imageUri, "drawable", packageName)
        phoneImage?.setImageResource(resourceImage)

        //populate views
        companyName.text = checkoutObj.phone.phoneMake
        modelName.text = checkoutObj.phone.phoneModel
        phoneColor.text = checkoutObj.phone.phoneColor
        internalStorage.text = checkoutObj.phone.storageCapacity

        price.text = checkoutObj.phone.getFormatterPrice()
        //populate views
        userName.text = checkoutObj.firstName+" "+checkoutObj.lastName
        address.text = checkoutObj.address
        city.text = checkoutObj.city
        postalCode.text = checkoutObj.postalCode

        val updateViewModel = ViewModelProvider(this).get(modelClass = UpdateCustumerViewModel::class.java)

        if(checkoutObj.isOrderDetail){
            findViewById<TableRow>(R.id.last_4_digits_row).visibility = View.GONE
            findViewById<TableRow>(R.id.card_type_row).visibility = View.GONE

            val orderDate = findViewById<TextView>(R.id.order_date)
            val orderDateRow = findViewById<TableRow>(R.id.order_date_row)

            orderDateRow.visibility = View.VISIBLE
            orderDate.text = Date(checkoutObj.orderModel!!.orderDate).toString()
            val titleTextView = findViewById<TextView>(R.id.title)
            titleTextView.text = "Order Details"
            supportActionBar?.title = "Order Details"
            val button = findViewById<Button>(R.id.login)

            button.text = "Cancel Order"
            if(checkoutObj.orderModel!!.status != "Ordered"){
                button.visibility = View.GONE
            }
        }
        else{

            cardType.text = checkoutObj.cardType.toString()
            last4Digits.text = checkoutObj.cardNumber?.substring(12)

            updateViewModel.liveCustomerData.observe(this, androidx.lifecycle.Observer {
                if(it != null){
                    val unixTime = System.currentTimeMillis()
                    var order = OrderModel(it.id!!, checkoutObj.phone.id!!, "Ordered",unixTime)
                    orderViewModel.addOrder(this,order)
                }
            })
            updateViewModel.getCustomer(this)
        }

    }

    fun onComplete(view: View) {
        if(checkoutObj.isOrderDetail) {
            if(checkoutObj.orderModel != null) {

                checkoutObj.orderModel!!.status = "Cancelled"
                var orderDate = Date(checkoutObj.orderModel!!.orderDate)
                var nowDate = Date(System.currentTimeMillis())

                //use the below link as reference
                //https://stackoverflow.com/questions/2003521/find-total-hours-between-two-dates
                var secs: Long = (nowDate.time - orderDate.time)/1000
                val hours = (secs / 3600).toInt()
                if(hours < 24) {
                    orderViewModel.updateOrder(this, checkoutObj.orderModel!!)
                    Toast.makeText(this,"Order Cancelled",Toast.LENGTH_SHORT).show()
                    finish()
                }
                else{
                    Toast.makeText(this,"You can only cancel an order within 24 hours",Toast.LENGTH_SHORT).show()
                }
            }
        }
        else{
            var intent = Intent(this, BottomNavigationActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}