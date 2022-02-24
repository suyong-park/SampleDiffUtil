package com.example.samplediffutil.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    private val _toastStr = SingleLiveEvent<String>()
    val toastStr : LiveData<String> = _toastStr

    private val _toastResStr = SingleLiveEvent<Int>()
    val toastResStr: LiveData<Int> = _toastResStr

    private val _toastResArguments = SingleLiveEvent<Pair<Int, Any>>()
    val toastResArguments : LiveData<Pair<Int, Any>> = _toastResArguments

    fun onClickUnimplementedFunc() {
        showToast("기능 구현 중입니다.")
    }

    private fun showToast(msg: String) = _toastStr.postValue(msg)

    protected fun showToast(resMsg: Int, args: Any? = null) {
        args?.let {
            _toastResArguments.postValue(resMsg to args)
        } ?: _toastResStr.postValue(resMsg)
    }

    companion object {
        private const val TAG = "BaseViewModel"
    }
}