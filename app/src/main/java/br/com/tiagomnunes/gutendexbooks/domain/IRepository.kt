package br.com.tiagomnunes.gutendexbooks.domain

import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getAllBooks(): Flow<List<Book>>
}