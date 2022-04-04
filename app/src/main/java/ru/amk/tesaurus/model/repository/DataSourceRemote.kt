package ru.amk.tesaurus.model.repository

import ru.amk.tesaurus.model.network.api.RetrofitImpl
import ru.amk.tesaurus.model.network.data.DataModel

class DataSourceRemote(
    private val remoteProvider: RetrofitImpl = RetrofitImpl()
) : DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> =
        remoteProvider.getData(word)
}
