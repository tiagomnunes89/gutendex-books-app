package br.com.tiagomnunes.gutendexbooks.data

import br.com.tiagomnunes.gutendexbooks.domain.Author

data class BookResult(
    val id: Int,
    val title: String,
    val authors: List<Author>,
    val subjects: List<String>
)
