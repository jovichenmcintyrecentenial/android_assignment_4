package com.centennial.jovichenmcintyre_mapd711_assignment4.ui.profile
//Name: Jovi Chen-Mcintyre
//ID: 301125059

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.centennial.jovichenmcintyre_mapd711_assignment4.R
import com.centennial.jovichenmcintyre_mapd711_assignment4.ui.udpate_customer.UpdateCustomerActivity
import com.centennial.jovichenmcintyre_mapd711_assignment4.ui.udpate_customer.UpdateCustumerViewModel

class ProfileFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var view = inflater.inflate(R.layout.fragment_profile, container, false)

        //find views
        var imageButton = view.findViewById<ImageButton>(R.id.imageButton)

        //click listener to navigate to update profile activity
        imageButton.setOnClickListener {

            startActivity(Intent(activity,UpdateCustomerActivity::class.java))
        }
        //connect to view model
        val updateViewModel = ViewModelProvider(this).get(modelClass = UpdateCustumerViewModel::class.java)

        //find views
        val username = view.findViewById<TextView>(R.id.uname)
        val name = view.findViewById<TextView>(R.id.name)

        //obeserve that update view when get customer name and email and display on profile UI
        updateViewModel.liveCustomerData.observe(viewLifecycleOwner, Observer {

            if(it != null) {
                username.text = "${it.email}"
                name.text = "${it.firstname} ${it.lastname}"
            }

        })
        //trigger get cusomter data
        activity?.let { updateViewModel.getCustomer(it) }
        return view

    }

    fun onEdit(view: View) {}

}