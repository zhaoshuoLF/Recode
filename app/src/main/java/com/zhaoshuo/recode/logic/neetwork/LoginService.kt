package com.zhaoshuo.recode.logic.neetwork

import com.zhaoshuo.recode.logic.model.LoginRequest
import com.zhaoshuo.recode.logic.model.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.*

interface LoginService {
    @POST ("/auth/signIn")
    @Headers(
        "Content-Type:application/json",
        "Refresh-Authorization:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRoVHlwZSI6InVzZXJzIiwiZW1haWwiOiI5OTYyNjcwNzdAcXEuY29tIiwiaWQiOiJiOWFjNzZiYy0wMjBjLTQyOWUtOTVmZi02MGU2NmE4NzZlZjAiLCJrZXlfaWQiOiI3OTkwYzBhNjM1ODg0ODMyOTgwMzc4ZWZlNzkzZTQxZSIsImVwb2NoIjoxNjY3MTgyODI0LCJwYXJ0bmVyX25hbWUiOiJhbmRyb2lkLWFwcCIsInR5cGUiOjAsImlhdCI6MTY2NzE4MjIyNCwiZXhwIjoxNjcyMzY2MjI0fQ.ZnM2XJNucrscTFEaDqt8Jf09j_bF5tUX9H3pP6U_9Wc",
        "Client:android-app",
        "x-app-version:02.04.02",
        "x-device-type:android"
    )
    fun loginRecode(@Body loginRequest: LoginRequest): Call<LoginResponse>
}