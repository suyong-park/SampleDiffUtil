package com.example.samplediffutil.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.samplediffutil.common.ext.showToast

abstract class BaseActivity<B : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    lateinit var binding: B

    abstract val layoutRes: Int
    abstract val viewModel: V

    abstract fun setBindingVariables(binding: B)
    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutRes)
        setBindingVariables(binding)
        binding.lifecycleOwner = this@BaseActivity

        initBaseObserver()
        initView()
    }

    private fun initBaseObserver() {
        with(viewModel) {
            toastStr.observe(this@BaseActivity) {
                this@BaseActivity.showToast(it)
            }
            toastResStr.observe(this@BaseActivity) {
                this@BaseActivity.showToast(it)
            }
            toastResArguments.observe(this@BaseActivity) {
                this@BaseActivity.showToast(it.first, it.second)
            }
        }
    }
}