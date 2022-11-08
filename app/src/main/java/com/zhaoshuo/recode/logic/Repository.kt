package com.zhaoshuo.recode.logic

import androidx.lifecycle.liveData
import com.zhaoshuo.recode.logic.dao.AuthorizationDao
import com.zhaoshuo.recode.logic.model.LoginRequest
import com.zhaoshuo.recode.logic.neetwork.RecodeNetWork
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.resume

object Repository {

     suspend fun loginRecode()=RecodeNetWork.loginRecode(LoginRequest("email","996267077@qq.com","123456789","02.04.02"))

}