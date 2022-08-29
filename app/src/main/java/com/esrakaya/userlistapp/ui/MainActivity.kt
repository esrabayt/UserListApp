package com.esrakaya.userlistapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.esrakaya.userlistapp.databinding.MainActivityBinding
import com.esrakaya.userlistapp.utils.PagingScrollListener
import com.esrakaya.userlistapp.utils.showSnackbar
import com.esrakaya.userlistapp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(MainActivityBinding::inflate)

    private val viewModel: MainViewModel by viewModels()

    private val mainAdapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        observeViewModel()
        viewModel.getPeople()
    }

    private fun initView() = with(binding) {
        swipeRefreshLayout.setOnRefreshListener { viewModel.onRefresh() }
        mainRecyclerView.apply {
            val layoutManager = layoutManager as LinearLayoutManager
            addOnScrollListener(object : PagingScrollListener(layoutManager) {
                override val lockLoadMore: Boolean
                    get() = viewModel.lockLoadMore

                override fun loadMoreItems() {
                    viewModel.getPeople()
                }
            })
            adapter = mainAdapter
        }
    }

    private fun observeViewModel() = with(viewModel) {
        viewStateLiveData.observe(this@MainActivity) { viewData ->
            viewData.items?.let { mainAdapter.submitList(viewData.items) }
            viewData.errorMessage?.let {
                binding.root.showSnackbar(viewData.errorMessage)
                onShownErrorMessage()
            }
            renderView(viewData)
        }
    }

    private fun renderView(viewData: MainViewData) = with(binding) {
        swipeRefreshLayout.isRefreshing = viewData.isRefreshing
        mainRecyclerView.isVisible = viewData.isItemsVisibility
        emptyCaseTextView.isVisible = viewData.isEmptyCaseVisibility
        loadingLayout.isVisible = viewData.isLoadingVisibility
    }
}