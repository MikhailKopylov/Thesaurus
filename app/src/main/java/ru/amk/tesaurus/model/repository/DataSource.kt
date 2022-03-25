package ru.amk.tesaurus.model.repository

import io.reactivex.Observable

interface DataSource<T> {

    fun getData(word: String): Observable<T>
}
