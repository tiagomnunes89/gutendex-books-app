package br.com.tiagomnunes.gutendexbooks.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "books")
data class Book(
    @PrimaryKey val id: Int,
    val title: String,
    val author: String,
    val birthYear: Int?,
    val deathYear: Int?,
    val subjects: List<String?>?
) {
    class Converters {
        @TypeConverter
        fun fromString(value: String): List<String> {
            val listType = object : TypeToken<List<String>>() {}.type
            return Gson().fromJson(value, listType)
        }

        @TypeConverter
        fun fromList(list: List<String>): String {
            return Gson().toJson(list)
        }
    }
}
