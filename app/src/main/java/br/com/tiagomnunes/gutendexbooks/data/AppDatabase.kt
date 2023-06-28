package br.com.tiagomnunes.gutendexbooks.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.tiagomnunes.gutendexbooks.domain.Book

@Database(entities = [Book::class], version = 1)
@TypeConverters(Book.Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}