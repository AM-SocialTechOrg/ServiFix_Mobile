package com.example.servifix_mobile.client

import androidx.constraintlayout.widget.Placeholder
import com.example.servifix_mobile.interfaces.PlaceHolder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val placeHolder: PlaceHolder by lazy {
        Retrofit.Builder()
            .baseUrl("https://servifix-api-docker.onrender.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlaceHolder::class.java)
    }
}