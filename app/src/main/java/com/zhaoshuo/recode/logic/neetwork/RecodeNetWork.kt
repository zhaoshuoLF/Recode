package com.zhaoshuo.recode.logic.neetwork

import com.zhaoshuo.recode.logic.dao.AuthorizationDao
import com.zhaoshuo.recode.logic.model.LoginRequest
import com.zhaoshuo.recode.logic.model.RecordResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object RecodeNetWork {
    private val loginService = ServiceCreator.create(LoginService::class.java)

    suspend fun loginRecode(loginRequest: LoginRequest) =
        loginService.loginRecode(loginRequest).await()

    private val recodeService = ServiceCreator.create<RecodeService>()

    suspend fun getRecodeList(
        pageSize: String,
        pageIndex: String
    ): RecordResponse {
//        val headers = hashMapOf<String, String>(
//            "Content-Type" to "application/json",
//            "Authorization" to "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRoVHlwZSI6InVzZXJzIiwiZW1haWwiOiI5OTYyNjcwNzdAcXEuY29tIiwiaWQiOiJiOWFjNzZiYy0wMjBjLTQyOWUtOTVmZi02MGU2NmE4NzZlZjAiLCJrZXlfaWQiOiI3OTkwYzBhNjM1ODg0ODMyOTgwMzc4ZWZlNzkzZTQxZSIsImVwb2NoIjoxNjY3OTc1NjMyLCJwYXJ0bmVyX25hbWUiOiJhbmRyb2lkLWFwcCIsInR5cGUiOjAsImlhdCI6MTY2Nzk3NTAzMiwiZXhwIjoxNjczMTU5MDMyfQ.IVXGn39wtYbQiiH0r7c5mZmj9bAsnMLZ89FvsIW8u3g"
//        )
        val token = AuthorizationDao.getSaveAuthorization() ?: ""
        val headers = hashMapOf<String, String>(
            "Content-Type" to "application/json",
            "Authorization" to token
        )
       val recordResponse:  RecordResponse=recodeService.getRecodeList(headers, pageSize, pageIndex)
        return recordResponse
    }

    private suspend fun <T : Any> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val headers = response.headers()
                    val body = response.body()
                    headers.get("refresh-authorization")
                        ?.let { AuthorizationDao.saveAuthorization(it) }
                    if (body != null) {
                        continuation.resume(body)
                    } else {
                        continuation.resumeWithException(RuntimeException("body or authorization is null"))
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }
    }
}