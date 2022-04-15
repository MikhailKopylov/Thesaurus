package ru.amk.tesaurus.presentation.interactors

interface Interactor<T> {

    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}
