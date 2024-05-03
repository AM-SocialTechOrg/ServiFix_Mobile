package com.example.servifix_mobile.interfaces

import com.example.servifix_mobile.beans.ApiResponse
import com.example.servifix_mobile.beans.LogInResponse
import com.example.servifix_mobile.beans.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PlaceHolder {
    @POST("auth/login")
    suspend fun logIn(@Body loginRequest: LoginRequest): ApiResponse<LogInResponse>
}