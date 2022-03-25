package ru.amk.tesaurus.model.repository

import io.reactivex.Observable
import ru.amk.tesaurus.model.network.data.DataModel

class RoomDataBaseImpl : DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}
