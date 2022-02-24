package com.example.samplediffutil.view

import android.util.Log
import com.example.samplediffutil.R
import com.example.samplediffutil.adapter.SampleAdapter
import com.example.samplediffutil.common.base.BaseActivity
import com.example.samplediffutil.common.model.SampleUser
import com.example.samplediffutil.databinding.ActivityMainBinding
import com.example.samplediffutil.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutRes = R.layout.activity_main
    override val viewModel = MainViewModel()

    private val sampleAdapter by lazy { SampleAdapter(viewModel) }

    override fun initView() {
        binding.sampleDataList.adapter = sampleAdapter

        sampleAdapter.submitList(
            listOf(
                SampleUser("김", "23", "남", "1"),
                SampleUser("이", "24", "남", "2"),
                SampleUser("박", "25", "여", "3")
            )
        )
    }

    fun addUser() {

        Log.e(TAG, "함수 호출 여부")

        val name = binding.name.text.toString()
        val age = binding.age.text.toString()
        val gender = binding.gender.text.toString()
        val education = binding.education.text.toString()

        sampleAdapter.submitList(
            listOf(
                SampleUser(name, age, gender, education)
            )
        )

        showInfo()
    }

    private fun showInfo() {
        viewModel.onAddUser()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}