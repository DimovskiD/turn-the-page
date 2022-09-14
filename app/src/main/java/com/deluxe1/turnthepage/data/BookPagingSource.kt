package com.deluxe1.turnthepage.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.deluxe1.turnthepage.data.model.Book

class BookPagingSource(
    private val query: String,
    private val repo: BookRepository
) : PagingSource<Int, Book>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        return try {
            if (query.isEmpty()) return LoadResult.Page(data = emptyList(), null, null)
            val nextPageNumber = params.key ?: 1
            val response = repo.getBooks(query, nextPageNumber, params.loadSize)

            LoadResult.Page(
                data = response.items,
                prevKey = params.key?.minus(1) ?: 0,
                nextKey = if (response.items.isNotEmpty()) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}