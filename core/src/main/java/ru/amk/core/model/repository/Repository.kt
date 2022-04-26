package ru.amk.core.model.repository

interface Repository<T> {

    suspend fun getData(word: String): T
}
