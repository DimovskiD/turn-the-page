package com.deluxe1.turnthepage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.deluxe1.turnthepage.data.BookPagingSource
import com.deluxe1.turnthepage.data.BookRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Random

private const val PAGE_SIZE = 10

class MainViewModel : ViewModel() {

    var query = mutableStateOf("")
        private set

    private val repo: BookRepository = BookRepository()
    private lateinit var pagingSource : BookPagingSource
    private val _valid : MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val valid : StateFlow<Boolean?> = _valid
    init {
        isUserDataValid()
    }
    val bookPager = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        BookPagingSource(query.value, repo).also { pagingSource = it }
    }.flow

    fun setQuery(query: String) {
        this.query.value = query
    }

    fun invalidateDataSource() {
        pagingSource.invalidate()
    }

    private fun isUserDataValid() = viewModelScope.launch {
        delay(1000)
        _valid.update { Random().nextBoolean() }
    }

    fun trackSplashScreenStarted() {
        //dummy method
    }
}