package ru.amk.tesaurus.model.repository

import ru.amk.tesaurus.model.network.data.DataModel

class DataSourceLocal(
    private val remoteProvider: RoomDataBaseImpl = RoomDataBaseImpl()
) : DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> =
        remoteProvider.getData(word)
}
