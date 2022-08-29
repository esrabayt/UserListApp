package com.esrakaya.userlistapp.ui

data class MainViewData(
    val items: List<MainItemViewData>? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val next: String? = null
) {
    val hasNextPage: Boolean
        get() = items.isNullOrEmpty() || next != null

    val isLoadingVisibility: Boolean
        get() = isLoading

    val isEmptyCaseVisibility: Boolean
        get() = items != null && items.isEmpty()

    val isItemsVisibility: Boolean
        get() = items != null && items.isNotEmpty()
}