package ru.amk.core.model.repository

interface DataSource<T> {

    suspend fun getData(word: String): T
}
