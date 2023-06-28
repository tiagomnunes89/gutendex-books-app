package br.com.tiagomnunes.gutendexbooks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.tiagomnunes.gutendexbooks.data.Repository
import br.com.tiagomnunes.gutendexbooks.domain.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    init {
        viewModelScope.launch {
            repository.getAllBooks().collect { books ->
                _books.value = books
            }
        }
    }

    fun clearBooksList() {
        _books.value = emptyList()
    }

}