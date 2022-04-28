package ru.amk.core.interactors

interface TranslateInteractor<T> {

    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}
