package com.example.servifix_mobile.beans

import java.util.Date

data class RegisterRequest (
    var firstName:String,
    var lastName:String,
    var gender : String,
    var birthday : String,
    var email:String,
    var password:String,
    var role:Int
)