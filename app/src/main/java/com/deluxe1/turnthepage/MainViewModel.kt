package com.deluxe1.turnthepage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.deluxe1.turnthepage.data.BookPagingSource
import com.deluxe1.turnthepage.data.BookRepository

private const val PAGE_SIZE = 10

class MainViewModel : ViewModel() {

    var query = mutableStateOf("")

    private val repo: BookRepository = BookRepository()
    private var pagingSource = BookPagingSource(query.value, repo)

    val bookPager = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        BookPagingSource(query.value, repo).also { pagingSource = it }
    }.flow

    fun setQuery(query: String) {
        this.query.value = query
    }

    fun invalidateDataSource() {
        pagingSource.invalidate()
    }
}