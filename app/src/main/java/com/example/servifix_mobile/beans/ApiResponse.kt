package com.example.servifix_mobile.beans

data class ApiResponse<T> (
    var message:String,
    var status: String,
    var data: T
)
