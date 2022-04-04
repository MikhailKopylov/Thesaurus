package ru.amk.tesaurus.model.repository

interface DataSource<T> {

    suspend fun getData(word: String): T
}
