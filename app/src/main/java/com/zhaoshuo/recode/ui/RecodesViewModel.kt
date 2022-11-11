package com.zhaoshuo.recode.ui

import androidx.lifecycle.*
import com.zhaoshuo.recode.logic.Repository
import com.zhaoshuo.recode.logic.model.RecordResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RecodesViewModel : ViewModel() {
    private val _groupList = MutableLiveData<
            Map<String?, List<RecordResponse.Item>>>()
    val groupList: LiveData<
            Map<String?, List<RecordResponse.Item>>> = _groupList

    private val _status = MutableLiveData("ALL")

    private val _isLogin = MutableLiveData<Boolean>(false)
    val isLogin: LiveData<Boolean> = _isLogin

    val recordList = Transformations.switchMap(_status) {
        MutableLiveData(_groupList.value?.get(it))
    }

    fun setCStatus(status: String) {
        _status.value = status
    }

    fun getRecodeList() = viewModelScope.launch {
        async {
            Repository.getRecodeList("100", "-1").onSuccess { data ->
                data?.let {
                    val map = data.items?.groupBy { it.status }
                    val mapT: MutableMap<String?, List<RecordResponse.Item>> = mutableMapOf()
                    data.items?.let { mapT.put("ALL", it) }
                    if (map != null) {
                        mapT.putAll(map)
                    }
                    _groupList.value = mapT
                }
            }.onFailure {
                _groupList.value = null
            }
        }
    }

    fun login() = viewModelScope.launch {
        async {
            Repository.loginRecode()
            _isLogin.value = true
        }
    }
}