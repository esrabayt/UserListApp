package com.esrakaya.userlistapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.esrakaya.userlistapp.databinding.MainActivityBinding
import com.esrakaya.userlistapp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(MainActivityBinding::inflate)

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        observeViewModel()
    }

    private fun initView() = with(binding) {

    }

    private fun observeViewModel() = with(viewModel) {

    }
}