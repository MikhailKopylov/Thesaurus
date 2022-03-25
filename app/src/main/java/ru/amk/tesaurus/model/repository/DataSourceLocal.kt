package ru.amk.tesaurus.model.repository

import io.reactivex.Observable
import ru.amk.tesaurus.model.network.data.DataModel

class DataSourceLocal(
    private val remoteProvider: RoomDataBaseImpl = RoomDataBaseImpl()
) : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> =
        remoteProvider.getData(word)
}
