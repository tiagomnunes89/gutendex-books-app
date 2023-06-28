package br.com.tiagomnunes.gutendexbooks.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import br.com.tiagomnunes.gutendexbooks.R
import br.com.tiagomnunes.gutendexbooks.components.AppBar
import br.com.tiagomnunes.gutendexbooks.components.BookDetails

@Composable
fun BookDetailScreen(
    bookId: Int,
    viewModel: BookViewModel,
    navController: NavController
) {
    val books by viewModel.books.collectAsState()
    val book = books.firstOrNull { it.id == bookId }

    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(id = R.string.book_details),
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.clearBooksList()
                        navController.navigate("bookList")
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = stringResource(id = R.string.back))
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            if (book != null) {
                BookDetails(book = book)
            } else {
                Text(text = "Book not found")
            }
        }
    }
}