package com.centennial.jovichenmcintyre_mapd711_assignment4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun register(view: View) {
        var intent = Intent(this,RegisterAcitivy::class.java)
        startActivity(intent)
    }
}