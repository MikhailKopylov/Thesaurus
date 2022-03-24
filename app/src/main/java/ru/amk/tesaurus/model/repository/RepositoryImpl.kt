package ru.amk.tesaurus.model.repository

import io.reactivex.Observable
import ru.amk.tesaurus.model.network.data.DataModel

class RepositoryImpl(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}
