package dev.juyoung.unsplash.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessScrollListener(
    private val layoutManager: RecyclerView.LayoutManager
) : RecyclerView.OnScrollListener() {
    private var visibleThreshold = 10
    private var currentPage = 1
    private var previousTotalCount = 0
    private var isLoading = true
    private var startPageIndex = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        var lastVisiblePosition = 0
        var totalItemCount = layoutManager.itemCount

        if (layoutManager is LinearLayoutManager) {
            lastVisiblePosition = layoutManager.findLastVisibleItemPosition()
        }

        if (totalItemCount < previousTotalCount) {
            currentPage = startPageIndex
            previousTotalCount = totalItemCount

            if (totalItemCount == 0) {
                isLoading = true
            }
        }

        if (isLoading && (totalItemCount > previousTotalCount)) {
            isLoading = false
            previousTotalCount = totalItemCount
        }

        if (!isLoading && (lastVisiblePosition + visibleThreshold) > totalItemCount) {
            currentPage++
            onNext(currentPage)
            isLoading = true
        }
    }

    abstract fun onNext(nextPage: Int)
}
