package com.centennial.jovichenmcintyre_mapd711_assignment4.utils
//Name: Jovi Chen-Mcintyre
//ID: 301125059

import UserInputException
import android.content.Context
import android.widget.EditText
import android.widget.Toast

class Utils {
    companion object {
        fun emptyValidation(edittext: EditText, error: String): Boolean {
            if (edittext.text.trim().isEmpty()) {
                throw UserInputException(error)
            }
            return false
        }

        //use to display toast messages
        fun showMessage(context:Context,message:String){
            Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
        }
    }

}