package com.deluxe1.turnthepage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.deluxe1.turnthepage.ui.MainScreen
import com.deluxe1.turnthepage.ui.composable.SearchView
import com.deluxe1.turnthepage.ui.theme.TurnThePageTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val books = mainViewModel.bookPager.collectAsLazyPagingItems()
            val focusManager = LocalFocusManager.current
            TurnThePageTheme {
                Scaffold(topBar = {
                    TopAppBar(
                        backgroundColor = MaterialTheme.colors.background
                    ) {
                        SearchView(
                            query = mainViewModel.query.value,
                            onQueryChanged = { newQuery ->
                                mainViewModel.setQuery(newQuery)
                            },
                            onSearch = {
                                mainViewModel.invalidateDataSource()
                                focusManager.clearFocus()
                            },
                            onClearQuery = {
                                mainViewModel.setQuery("")
                                mainViewModel.invalidateDataSource()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = MaterialTheme.colors.background,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        )
                    }
                }) { padding ->
                    MainScreen(books = books, modifier = Modifier.padding(padding))
                }
            }
        }
    }
}
