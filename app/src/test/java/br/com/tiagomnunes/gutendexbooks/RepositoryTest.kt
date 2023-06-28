package br.com.tiagomnunes.gutendexbooks

import br.com.tiagomnunes.gutendexbooks.data.BookDao
import br.com.tiagomnunes.gutendexbooks.data.BookResponse
import br.com.tiagomnunes.gutendexbooks.data.BookResult
import br.com.tiagomnunes.gutendexbooks.data.GutendexService
import br.com.tiagomnunes.gutendexbooks.data.Repository
import br.com.tiagomnunes.gutendexbooks.domain.Author
import br.com.tiagomnunes.gutendexbooks.domain.Book
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class RepositoryTest {
    private val bookDao = mockk<BookDao>()
    private val repository = Repository(bookDao)
    private val gutendexService = mockk<GutendexService>()

    @Test
    fun `when database is empty, books are fetched from API and inserted into database`() = runBlocking {
        // Given
        val booksFlow = MutableStateFlow(emptyList<Book>())
        every { bookDao.getAllBooks() } returns booksFlow

        val bookResults = listOf(
            BookResult(
                id = 1,
                title = "Book 1",
                authors = listOf(Author(name = "Author 1", birthYear = 1900, deathYear = 2000)),
                subjects = listOf("Subject 1")
            ),
            BookResult(
                id = 2,
                title = "Book 2",
                authors = listOf(Author(name = "Author 2", birthYear = 1900, deathYear = 2000)),
                subjects = listOf("Subject 2")
            )
        )

        // When
        coEvery { gutendexService.getBooks() } returns BookResponse(bookResults)
        coEvery { bookDao.insertBooks(any()) } just Runs

        val bookResponse = gutendexService.getBooks()
        val booksResult = bookResponse.results.map { book: BookResult ->
            Book(
                id = book.id,
                title = book.title,
                author = book.authors.first().name,
                birthYear = book.authors.first().birthYear,
                deathYear = book.authors.first().deathYear,
                subjects = book.subjects
            )
        }
        booksFlow.emit(booksResult)

        // Then
        val books = repository.getAllBooks().first()

        assertEquals(2, books.size)
        assertEquals("Book 1", books[0].title)
        assertEquals("Book 2", books[1].title)
    }

    @Test
    fun `when database is not empty, books are not fetched from API`() = runBlocking {
        // Given
        val books = listOf(
            Book(
                id = 1,
                title = "Book 1",
                author = "Author 1",
                birthYear = 1900,
                deathYear = 2000,
                subjects = listOf("Subject 1")
            ),
            Book(
                id = 2,
                title = "Book 2",
                author = "Author 2",
                birthYear = 1900,
                deathYear = 2000,
                subjects = listOf("Subject 2")
            )
        )
        val booksFlow = flowOf(books)
        every { bookDao.getAllBooks() } returns booksFlow

        // When
        val result = repository.getAllBooks().first()

        // Then
        coVerify(exactly = 0) { gutendexService.getBooks() }
        assert(result == books)
    }
}