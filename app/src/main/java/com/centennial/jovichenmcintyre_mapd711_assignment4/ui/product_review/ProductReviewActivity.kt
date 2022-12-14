package com.centennial.jovichenmcintyre_mapd711_assignment4.ui.product_review
//Name: Jovi Chen-Mcintyre
//ID: 301125059
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.centennial.jovichenmcintyre_mapd711_assignment4.R
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.PhoneCheckOut
import com.centennial.jovichenmcintyre_mapd711_assignment4.ui.checkout.CheckOutActivity
import com.google.gson.Gson

class ProductReviewActivity : AppCompatActivity() {

    //declare views
    lateinit var  selectColorPhone:String
    lateinit var  checkoutObj: PhoneCheckOut

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_review)
        //set title
        supportActionBar?.title = getString(R.string.product_review)


        //deserialize PhoneCheckOut in varible checkoutObj
        checkoutObj = Gson().fromJson(intent.getStringExtra("checkout"),PhoneCheckOut::class.java)

        //find views
        var phoneImageView = findViewById<ImageView>(R.id.phone_image)
        var phoneNameTextView = findViewById<TextView>(R.id.phone_name)
        var radioButton = findViewById<TextView>(R.id.opt_64GB)
        val spinner: Spinner = findViewById(R.id.color_spinner)
        // data to populate the RecyclerView with
        // data to populate the RecyclerView with
        val colors: ArrayList<String> = ArrayList()
        colors.add(checkoutObj.phone.phoneColor!!)
        //load spinner with string array daata
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,colors
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        radioButton.text = checkoutObj.phone.storageCapacity

        //add click listener to save select value when user selects a color from spinner
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectColorPhone = colors[0]
            }

        }

        //display phone name in view
        phoneNameTextView.text = checkoutObj.phone.phoneModel

        //use phone.uri from phone object to get resourceImage based on uri
        val resourceImage: Int = resources.getIdentifier(checkoutObj.phone.imageUri, "drawable", packageName)

        //set image from for phone
        phoneImageView?.setImageResource(resourceImage)

    }


    fun onSubmit(view: View) {
        //create new intent to CheckOutActivity
        var newIntent = Intent(this, CheckOutActivity::class.java )

        //serialize checkoutObj and save to intent
        newIntent.putExtra("checkout",Gson().toJson(checkoutObj))
        //start intent
        startActivity(newIntent)
    }
}