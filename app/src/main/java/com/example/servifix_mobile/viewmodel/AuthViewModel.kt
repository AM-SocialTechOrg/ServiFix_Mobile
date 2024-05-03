package com.example.servifix_mobile.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.servifix_mobile.beans.LogInResponse
import com.example.servifix_mobile.beans.LoginRequest
import com.example.servifix_mobile.beans.RegisterRequest
import com.example.servifix_mobile.client.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {
    private val placeholderService = RetrofitClient.placeHolder
    var loginres: LogInResponse = LogInResponse("")

    fun logIn(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)
        Log.e("LoginViewModel", loginRequest.toString())

        viewModelScope.launch(Dispatchers.IO) {
            val response = placeholderService.logIn(loginRequest)

            withContext(Dispatchers.Main) {
                if(response!=null) {
                    loginres.token = response.data.token
                    Log.e("AuthViewModel", "res: ${response.data.token}")
                }

            }
        }
    }

    fun register(
        first_name: String, last_name: String, gender: String, birthday: String,
        email:String, password:String, role: Int) {
        val registerRequest = RegisterRequest(first_name, last_name, gender, birthday, email, password, role)
        Log.e("AuthViewModel", registerRequest.toString())

        viewModelScope.launch(Dispatchers.IO) {
            val response = placeholderService.register(registerRequest)

            withContext(Dispatchers.Main) {
                if(response!=null) {
                    Log.e("AuthViewModel", "res: ${response.data}")
                }
            }
        }
    }


}