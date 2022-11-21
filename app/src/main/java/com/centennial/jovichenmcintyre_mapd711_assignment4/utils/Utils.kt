package com.centennial.jovichenmcintyre_mapd711_assignment4.utils

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import com.centennial.jovichenmcintyre_mapd711_001_assignment2.exceptions.UserInputException

class Utils {
    companion object {
        fun _isEmptyValidation(edittext: EditText, error: String): Boolean {
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