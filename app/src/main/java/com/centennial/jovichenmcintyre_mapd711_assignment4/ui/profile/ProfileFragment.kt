package com.centennial.jovichenmcintyre_mapd711_assignment4.ui.profile

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
import com.centennial.jovichenmcintyre_mapd711_assignment4.ui.home.HomeViewModel
import com.centennial.jovichenmcintyre_mapd711_assignment4.ui.register.RegisterAcitivy
import com.centennial.jovichenmcintyre_mapd711_assignment4.ui.udpate_customer.UpdateCustomerActivity
import com.centennial.jovichenmcintyre_mapd711_assignment4.ui.udpate_customer.UpdateCustumerViewModel
import org.w3c.dom.Text

class ProfileFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var view = inflater.inflate(R.layout.fragment_profile, container, false)
        var imageButton = view.findViewById<ImageButton>(R.id.imageButton)

        imageButton.setOnClickListener {

            startActivity(Intent(activity,UpdateCustomerActivity::class.java))
        }

        val updateViewModel = ViewModelProvider(this).get(modelClass = UpdateCustumerViewModel::class.java)

        val username = view.findViewById<TextView>(R.id.uname)
        val name = view.findViewById<TextView>(R.id.name)
        updateViewModel.liveCustomerData.observe(viewLifecycleOwner, Observer {

            if(it != null) {
                username.text = "@${it.userName}"
                name.text = "${it.firstname} ${it.lastname}"
            }

        })
        activity?.let { updateViewModel.getCustomer(it) }
        return view

    }

    fun onEdit(view: View) {}

}