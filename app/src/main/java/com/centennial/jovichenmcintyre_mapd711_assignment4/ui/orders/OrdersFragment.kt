package com.centennial.jovichenmcintyre_mapd711_assignment4.ui.orders

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
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.CustomerModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.PhoneCheckOut
import com.centennial.jovichenmcintyre_mapd711_assignment4.models.ProductOrder
import com.centennial.jovichenmcintyre_mapd711_assignment4.ui.order_summary.OrderSummaryActivity
import com.google.gson.Gson

class OrdersFragment : Fragment() {

    var ordersViewModel:OrdersViewModel? = null
    var customerID: Int? = null
    var list:MutableList<ProductOrder>? = null

    override fun onResume() {
        super.onResume()
        if(ordersViewModel!=null){
            context?.let { ordersViewModel!!.getOrders(it,customerID!!) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        ordersViewModel =
        ViewModelProvider(this).get(OrdersViewModel::class.java)

        var view = inflater.inflate(R.layout.fragment_orders, container, false)
        var listView = view.findViewById<ListView>(R.id.list)

        ordersViewModel!!.liveCustomerData.observe(viewLifecycleOwner, Observer { customerModel ->
            customerID = customerModel!!.id
            if(customerModel != null){
                ordersViewModel!!.listOfOrdersLiveData.observe(viewLifecycleOwner,Observer{ listOfOrders ->
                    if(listOfOrders != null){

                        var listAdaptor = activity?.let { activity ->
                            OrderListAdaptor(activity, listOfOrders, customerModel)
                        }
                        if(list == null){
                            list = listOfOrders as MutableList<ProductOrder>?
                        }
                        //attach adaptor to listview
                        if(listView.adapter == null) {
                            listView.adapter = listAdaptor
                        }else{
                            list!!.clear()
                            list!!.addAll(listOfOrders)
                            listView.invalidate()
                            listView.invalidateViews()
                        }

                    }
                })
                context?.let { ordersViewModel!!.getOrders(it,customerModel.id!!) }
            }

        })
        context?.let { ordersViewModel!!.getCustomer(it) }
        //create a listener for on click aciton on list view
        listView.setOnItemClickListener { parent, view, position, id ->
            var newIntent = Intent(activity, OrderSummaryActivity::class.java)
            var productOrder = ordersViewModel!!.listOfOrdersLiveData.value!![position]
            var customerModel = ordersViewModel!!.liveCustomerData.value!!

            var checkoutObj = PhoneCheckOut(productOrder.productModel!!)

            checkoutObj.address = customerModel.address
            checkoutObj.city = customerModel.city
            checkoutObj.postalCode = customerModel.postal

            checkoutObj.firstName = customerModel.firstname
            checkoutObj.lastName = customerModel.lastname

            checkoutObj.orderModel = productOrder.orderModel
            checkoutObj.isOrderDetail = true

            //update create PhoneCheckOut and serialize data and pass to intent
            newIntent.putExtra("checkout", Gson().toJson(checkoutObj))
            //load new Intent
            startActivity(newIntent)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
    class OrderListAdaptor(context: Activity, list:List<ProductOrder>,customerModel: CustomerModel):  BaseAdapter(){

        var context = context
        var list = list
        var customerModel = customerModel

        override fun getCount(): Int {
            return list.count()
        }

        override fun getItem(position: Int): ProductOrder {
            return list[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            var inflatedView:View? = convertView
            //load data at position
            val productOrder = list[position]


            if(inflatedView == null){
                //inflate custom list item layout
                inflatedView = LayoutInflater.from(context).
                inflate(R.layout.order_list_item, parent, false)
            }

            //find views
            val priceTextView = inflatedView?.findViewById<TextView>(R.id.price)
            val phoneImage = inflatedView?.findViewById<ImageView>(R.id.image)
            val name = inflatedView?.findViewById<TextView>(R.id.name)
            val address1 = inflatedView?.findViewById<TextView>(R.id.address1)
            val address2 = inflatedView?.findViewById<TextView>(R.id.address2)
            val orderStatus = inflatedView?.findViewById<TextView>(R.id.order_status)

            //dynamically load phone images using phone uri
            val resourceImage: Int = context.resources.getIdentifier(productOrder.productModel!!.imageUri, "drawable", context.packageName)
            phoneImage?.setImageResource(resourceImage)

            //update phone name in list
            name?.text = productOrder.productModel!!.phoneModel
            orderStatus?.text = productOrder.orderModel!!.status
            address1?.text = customerModel.address
            address2?.text = customerModel.postal

            //update price on list time
            priceTextView?.text = productOrder.productModel!!.getFormatterPrice()
            return inflatedView!!
        }

    }

}

//custom list adaptor to achieve design
