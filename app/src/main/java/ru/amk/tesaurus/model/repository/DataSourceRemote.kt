package ru.amk.tesaurus.model.repository

import io.reactivex.Observable
import ru.amk.tesaurus.model.network.api.RetrofitImpl
import ru.amk.tesaurus.model.network.data.DataModel

class DataSourceRemote(
    private val remoteProvider: RetrofitImpl = RetrofitImpl()
) : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> =
        remoteProvider.getData(word)
}
