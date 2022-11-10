package com.zhaoshuo.recode.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhaoshuo.recode.logic.Repository
import com.zhaoshuo.recode.logic.model.RecordResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RecodesViewModel : ViewModel() {
    private val _list = MutableLiveData<RecordResponse.Data>()
    val list: LiveData<RecordResponse.Data> = _list

    private val _isLogined = MutableLiveData<Boolean>(false)
    val isLogined: LiveData<Boolean> = _isLogined

    fun getRecodeList() = viewModelScope.launch {
        async {
            Repository.getRecodeList("100", "-1").onSuccess { data ->
                _list.value = data
            }.onFailure {
                _list.value = null
            }
        }
    }

    fun login() = viewModelScope.launch {
        async {
            Repository.loginRecode()
            _isLogined.value = true
        }
    }
}