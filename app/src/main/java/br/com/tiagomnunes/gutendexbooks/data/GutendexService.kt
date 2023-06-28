package br.com.tiagomnunes.gutendexbooks.data

import retrofit2.http.GET

interface GutendexService {
    @GET("books")
    suspend fun getBooks(): BookResponse
}