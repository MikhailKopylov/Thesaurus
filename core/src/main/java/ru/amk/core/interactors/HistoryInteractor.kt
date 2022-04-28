package ru.amk.core.interactors

interface HistoryInteractor<T> {

    suspend fun getData(): T

    suspend fun saveData(data: String)
}
