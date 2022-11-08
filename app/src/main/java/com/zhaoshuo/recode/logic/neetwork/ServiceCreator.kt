package com.zhaoshuo.recode.logic.neetwork

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
//    private val httpClient = OkHttpClient.Builder().addInterceptor {
//        it.proceed(
//            it.request().newBuilder()
//                .header("Content-Type","application/json")
//                .header("Refresh-Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRoVHlwZSI6InVzZXJzIiwiZW1haWwiOiI5OTYyNjcwNzdAcXEuY29tIiwiaWQiOiJiOWFjNzZiYy0wMjBjLTQyOWUtOTVmZi02MGU2NmE4NzZlZjAiLCJrZXlfaWQiOiI3OTkwYzBhNjM1ODg0ODMyOTgwMzc4ZWZlNzkzZTQxZSIsImVwb2NoIjoxNjY3MTgyODI0LCJwYXJ0bmVyX25hbWUiOiJhbmRyb2lkLWFwcCIsInR5cGUiOjAsImlhdCI6MTY2NzE4MjIyNCwiZXhwIjoxNjcyMzY2MjI0fQ.ZnM2XJNucrscTFEaDqt8Jf09j_bF5tUX9H3pP6U_9Wc")
//                .header("Client","android-app")
//                .header("x-app-version","02.04.02")
//                .header("x-device-type","android")
//                .method(it.request().method(), it.request().body())
//                .build()
//        )
//    }

    private const val BASE_URL = "https://dhp.tmodevedge.com"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}