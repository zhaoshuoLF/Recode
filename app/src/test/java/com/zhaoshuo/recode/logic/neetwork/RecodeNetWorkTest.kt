package com.zhaoshuo.recode.logic.neetwork

import com.zhaoshuo.recode.logic.dao.AuthorizationDao
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

    @Test
    fun getRecodeList() {
        runBlocking {
//            val headers= mapOf<String,String>("Content-Type" to "application/json","Authorization" to "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRoVHlwZSI6InVzZXJzIiwiZW1haWwiOiI5OTYyNjcwNzdAcXEuY29tIiwiaWQiOiJiOWFjNzZiYy0wMjBjLTQyOWUtOTVmZi02MGU2NmE4NzZlZjAiLCJrZXlfaWQiOiI3OTkwYzBhNjM1ODg0ODMyOTgwMzc4ZWZlNzkzZTQxZSIsImVwb2NoIjoxNjY3OTU4MDg1LCJwYXJ0bmVyX25hbWUiOiJhbmRyb2lkLWFwcCIsInR5cGUiOjAsImlhdCI6MTY2Nzk1NzQ4NSwiZXhwIjoxNjczMTQxNDg1fQ.RoBbVMqKoUtf5YE80HO-c0jIRCSf2ZrEHq5O2tWoVlU")

            RecodeNetWork.getRecodeList("100","-1")
        }
    }
}