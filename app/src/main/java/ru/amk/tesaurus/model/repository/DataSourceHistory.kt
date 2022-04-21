package ru.amk.tesaurus.model.repository

interface DataSourceHistory<T> {

    suspend fun getData(): T
    suspend fun saveData(word: String)
}
