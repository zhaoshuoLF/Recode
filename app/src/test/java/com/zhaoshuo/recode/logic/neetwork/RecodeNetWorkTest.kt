package com.zhaoshuo.recode.logic.neetwork

import com.zhaoshuo.recode.logic.model.LoginRequest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test

class RecodeNetWorkTest {

    @Test
    fun loginRecode() {
        runBlocking {
            RecodeNetWork.loginRecode(LoginRequest("email","996267077@qq.com","123456789","02.04.02"))
        }
    }
}