package com.example.servifix_mobile.beans

import java.util.Date

data class RegisterResponse (
    var id: Int,
    var first_name:String,
    var last_name:String,
    var gender : String,
    var birthday : String,
    var email:String,
    var password:String,
    var role:Role
)

data class Role(
    var id:Int,
    var name:String
)