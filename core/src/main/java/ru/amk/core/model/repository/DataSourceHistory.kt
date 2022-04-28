package ru.amk.core.model.repository

interface DataSourceHistory<T> {

    suspend fun getData(): T
    suspend fun saveData(word: String)
}
