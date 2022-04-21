package ru.amk.tesaurus.presentation.interactors

interface TranslateInteractor<T> {

    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}
