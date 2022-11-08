package com.zhaoshuo.recode.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.zhaoshuo.recode.RecodeApplication

object AuthorizationDao {
    fun saveAuthorization(authorization: String) {
        this.sharedPreferences().edit() {
            putString("cookie", authorization)
        }

    }

    fun getSaveAuthorization() = sharedPreferences().getString("cookie", "")

    private fun sharedPreferences() =
        RecodeApplication.context.getSharedPreferences("recode", Context.MODE_PRIVATE)
}