package com.example.samplediffutil.view

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
        setDummyData()
    }

    private fun setDummyData() {
        sampleAdapter.submitList(
            listOf(
                SampleUser("아무개", "20", "남", "한국대학교"),
                SampleUser("홍길동", "21", "여", "한국대"),
                SampleUser("박수용", "22", "남", "명지대")
            )
        )
    }
    override fun setBindingVariables(binding: ActivityMainBinding) {
        binding.mainActivity = this
    }

    fun onClickAddUser() {
        with(binding) {
            val name = name.text.toString()
            val age = age.text.toString()
            val gender = gender.text.toString()
            val education = education.text.toString()

            with(sampleAdapter) {
                val currentList = currentList.toMutableList()
                currentList.add(
                    SampleUser(name, age, gender, education)
                )

                submitList(currentList)
                notifyItemInserted(currentList.size - 1)
            }
        }

        with(viewModel) {
            onSuccessAddUser()
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}