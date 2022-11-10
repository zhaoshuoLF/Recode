package com.zhaoshuo.recode.logic

import androidx.lifecycle.liveData
import com.zhaoshuo.recode.logic.model.LoginRequest
import com.zhaoshuo.recode.logic.model.RecordResponse
import com.zhaoshuo.recode.logic.neetwork.RecodeNetWork
import kotlin.coroutines.CoroutineContext

object Repository {

    suspend fun loginRecode() = RecodeNetWork.loginRecode(
        LoginRequest(
            "email",
            "996267077@qq.com",
            "123456789",
            "02.04.02"
        )
    )

    suspend fun getRecodeList(
        pageSize: String,
        pageIndex: String
    ): Result<RecordResponse.Data?> {
        val result: Result<RecordResponse.Data?> = try {
            val recodeResponse = RecodeNetWork.getRecodeList(pageSize, pageIndex)
            if (recodeResponse.code == "200") {
                val data = recodeResponse.data
                Result.success(data)
            } else {
                Result.failure(RuntimeException("response status is ${recodeResponse.code}"))
            }
        } catch (exception: Exception) {
            Result.failure(RuntimeException("response is null"))
        }

        return result
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
}