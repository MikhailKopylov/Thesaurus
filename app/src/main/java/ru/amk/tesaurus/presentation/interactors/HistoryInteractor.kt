package ru.amk.tesaurus.presentation.interactors

interface HistoryInteractor<T> {

    suspend fun getData(): T

    suspend fun saveData(data: String)
}
