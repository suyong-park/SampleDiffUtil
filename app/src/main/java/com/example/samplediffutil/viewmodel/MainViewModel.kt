package com.example.samplediffutil.viewmodel

import com.example.samplediffutil.R
import com.example.samplediffutil.common.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    fun onSuccessAddUser() {
        showToast(R.string.success)
    }
}