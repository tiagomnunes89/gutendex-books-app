package br.com.tiagomnunes.gutendexbooks.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.tiagomnunes.gutendexbooks.R
import br.com.tiagomnunes.gutendexbooks.domain.Book

@Composable
fun BookDetails(book: Book) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(text = book.title, style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Author: ${book.author}", style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Birth Year: ${book.birthYear ?: stringResource(R.string.unknown)}", style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Death Year: ${book.deathYear ?: stringResource(R.string.unknown)}", style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Subject: ${book.subjects?.first() ?: stringResource(R.string.unknown)}", style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(8.dp))
    }
}