package ru.amk.tesaurus.model.network.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.amk.tesaurus.model.network.data.DataModel

interface ApiService {
    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<DataModel>>
}
