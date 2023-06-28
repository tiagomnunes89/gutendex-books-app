package br.com.tiagomnunes.gutendexbooks.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import br.com.tiagomnunes.gutendexbooks.R
import br.com.tiagomnunes.gutendexbooks.components.AppBar
import br.com.tiagomnunes.gutendexbooks.components.BookListItem

@Composable
fun BookListScreen(navController: NavController, viewModel: BookViewModel) {
    val books by viewModel.books.collectAsState(initial = emptyList())

    Scaffold(
        topBar = { AppBar(title = stringResource(R.string.app_name)) }
    ) {
        LazyColumn (
            Modifier.padding(it)
                ) {
            items(books) { book ->
                BookListItem(book) {
                    navController.navigate("bookDetail/${book.id}")
                }
            }
        }
    }
}

