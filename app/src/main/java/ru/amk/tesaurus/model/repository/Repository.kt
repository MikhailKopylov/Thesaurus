package ru.amk.tesaurus.model.repository

interface Repository<T> {

    suspend fun getData(word: String): T
}
