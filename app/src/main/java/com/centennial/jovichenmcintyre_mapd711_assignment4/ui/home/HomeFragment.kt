package com.centennial.jovichenmcintyre_mapd711_assignment4.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.centennial.jovichenmcintyre_mapd711_assignment4.R
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.ProductModel
import com.google.gson.Gson

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(modelClass = HomeViewModel::class.java)


        val view = inflater.inflate(R.layout.fragment_home, container, false)

        //find list view
        var listView = view.findViewById<ListView>(R.id.list)

//        //create a listener for on click aciton on list view
//        listView.setOnItemClickListener { parent, view, position, id ->
//            var newIntent = Intent(this,PhoneOptionsSelectActivity::class.java)
//            //update create PhoneCheckOut and serialize data and pass to intent
//            newIntent.putExtra("checkout", Gson().toJson(PhoneCheckOut(listOfPhones[position])))
//            //load new Intent
//            startActivity(newIntent)
//        }

        activity?.let { homeViewModel.listOfProductLiveData.observe(it, Observer { listOfPhones ->
            if(listOfPhones != null) {

                //create instance of a custom listAdpator called PhoneListAdaptor
                var listAdaptor = activity?.let { activity ->
                    PhoneListAdaptor(activity, listOfPhones)
                }

                //attact adaptor to listview
                listView.adapter = listAdaptor
            }


        }) }

        activity?.let { homeViewModel.getProducts(it) }


        return view
    }

    //custom list adaptor to achieve design
    class PhoneListAdaptor(context: Activity, list:List<ProductModel>):  BaseAdapter(){

        var context = context
        var list = list

        override fun getCount(): Int {
            return list.count()
        }

        override fun getItem(position: Int): ProductModel {
            return list[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            var inflatedView:View? = convertView
            //load data at position
            val phone = list[position]


            if(inflatedView == null){
                //inflate custom list item layout
                inflatedView = LayoutInflater.from(context).
                inflate(R.layout.phone_list_item, parent, false)
            }

            //find views
            val priceTextView = inflatedView?.findViewById<TextView>(R.id.phone_price)
            val phoneImage = inflatedView?.findViewById<ImageView>(R.id.phone_image)
            val phoneNameTextView = inflatedView?.findViewById<TextView>(R.id.phone_name)

            //dynamically load phone images using phone uri
            val resourceImage: Int = context.resources.getIdentifier(phone.imageUri, "drawable", context.packageName)
            phoneImage?.setImageResource(resourceImage)

            //update phone name in list
            phoneNameTextView?.text = phone.phoneModel
            //update price on list time
            priceTextView?.text = phone.getFormatterPrice()
            return inflatedView!!
        }

    }

}