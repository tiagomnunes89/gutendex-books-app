package br.com.tiagomnunes.gutendexbooks

import br.com.tiagomnunes.gutendexbooks.data.Repository
import br.com.tiagomnunes.gutendexbooks.domain.Book
import br.com.tiagomnunes.gutendexbooks.presentation.BookViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BookViewModelTest {
    private val repository = mockk<Repository>()
    private lateinit var viewModel: BookViewModel

    @get:Rule
    val coroutineRule = TestCoroutineRule()

    @Before
    fun setUp() {
        every { repository.getAllBooks() } returns flowOf(emptyList())
        viewModel = BookViewModel(repository)
    }

    @Test
    fun `when books are emitted from repository, books value is updated`() = coroutineRule.run {
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
        every { repository.getAllBooks() } returns flowOf(books)

        // When
        viewModel = BookViewModel(repository)

        // Then
        assertEquals(books, viewModel.books.value)
    }

    @Test
    fun `when clearBooksList is called, books value is set to empty list`() {
        // When
        viewModel.clearBooksList()

        // Then
        assertTrue(viewModel.books.value.isEmpty())
    }
}
