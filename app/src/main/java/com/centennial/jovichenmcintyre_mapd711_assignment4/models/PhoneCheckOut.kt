package com.centennial.jovichenmcintyre_mapd711_assignment4.models

import CardType

//Name: Jovi Chen-Mcintyre
//ID: 301125059

class PhoneCheckOut (phone:ProductModel) {
    var cardType: CardType? = null
    var phone = phone
    var color:String? = null
    var cardNumber:String? = null
    var internalStorageSize:String? = null
    var cvv:String? = null
    var firstName:String? = null
    var lastName:String? = null
    var address:String? = null
    var city:String? = null
    var postalCode:String? = null
    var telephone:String? = null
    var expirationMonth : String? = null
    var expirationYear : String? = null
    var type :String? = null
    var isOrderDetail = false
    var orderModel:OrderModel? = null

}