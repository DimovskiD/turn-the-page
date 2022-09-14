package com.deluxe1.turnthepage.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.deluxe1.turnthepage.R
import com.deluxe1.turnthepage.data.model.Book
import com.deluxe1.turnthepage.ui.composable.BookItem
import com.deluxe1.turnthepage.ui.theme.LightGray
import com.deluxe1.turnthepage.ui.theme.TurnThePageTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun MainScreen(books: LazyPagingItems<Book>, modifier: Modifier = Modifier) {
    when (books.loadState.refresh) {
        LoadState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        is LoadState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(R.string.something_went_wrong))
            }
        }
        else -> {
            LazyColumn(modifier = modifier) {
                itemsIndexed(books) { index, item ->
                    item?.let {
                        BookItem(
                            book = item,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(getBackgroundForIndex(index))
                                .padding(vertical = 15.dp)
                        )
                    }
                }
            }
        }
    }
}

fun getBackgroundForIndex(index: Int) =
    if (index % 2 == 0) LightGray
    else Color.White

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TurnThePageTheme {
        MainScreen(
            flowOf(
                PagingData.from(
                    listOf(
                        Book()
                    )
                )
            ).collectAsLazyPagingItems()
        )
    }
}