package br.com.tiagomnunes.gutendexbooks.data

import br.com.tiagomnunes.gutendexbooks.domain.Book
import br.com.tiagomnunes.gutendexbooks.domain.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Repository @Inject constructor(
    private val bookDao: BookDao,
) : IRepository {
    override fun getAllBooks(): Flow<List<Book>> {
        return bookDao.getAllBooks()
            .map { books ->
                if (books.isEmpty()) {
                    val response = GutendexApi.service.getBooks()
                    val result = response.results.map { book: BookResult ->
                        Book(
                            id = book.id,
                            title = book.title,
                            author = book.authors.first().name,
                            birthYear = book.authors.first().birthYear,
                            deathYear = book.authors.first().deathYear,
                            subjects = book.subjects
                        )
                    }
                    bookDao.insertBooks(result)
                }
                books
            }
    }
}