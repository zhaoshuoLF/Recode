package com.zhaoshuo.recode.logic.neetwork

import com.zhaoshuo.recode.logic.dao.AuthorizationDao
import com.zhaoshuo.recode.logic.model.LoginRequest
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

    private suspend fun <T : Any> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val headers = response.headers()
                    val body = response.body()
                    val authorization = headers.get("refresh-authorization")
                    if (body != null && authorization != null) {
                        AuthorizationDao.saveAuthorization(authorization)
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