package com.centennial.jovichenmcintyre_mapd711_assignment4.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.centennial.jovichenmcintyre_mapd711_assignment4.R

class OrdersFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(OrdersViewModel::class.java)

        return inflater.inflate(R.layout.fragment_dashboard, container, false)

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}