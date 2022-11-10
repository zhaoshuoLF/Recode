package com.zhaoshuo.recode.logic.neetwork

import com.zhaoshuo.recode.logic.model.RecordResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface RecodeService {
    @GET("/appointmentV2/list")
   suspend fun getRecodeList(
        @HeaderMap headers: HashMap<String, String>?,
        @Query("pageSize") pageSize: String,
        @Query("pageIndex") pageIndex: String
    ): RecordResponse
}