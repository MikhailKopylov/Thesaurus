package ru.amk.tesaurus.model.repository

import io.reactivex.Observable

interface Repository <T> {

    fun getData(word: String): Observable<T>
}
