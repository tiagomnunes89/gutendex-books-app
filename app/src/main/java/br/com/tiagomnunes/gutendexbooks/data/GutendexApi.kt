package br.com.tiagomnunes.gutendexbooks.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GutendexApi {
    private const val BASE_URL = "https://gutendex.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: GutendexService by lazy { retrofit.create(GutendexService::class.java) }
}