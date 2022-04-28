package ru.amk.core.model.repository

import ru.amk.core.model.network.data.DataModel

class DataSourceLocal(
    private val remoteProvider: RoomDataBaseImpl = RoomDataBaseImpl()
) : DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> =
        remoteProvider.getData(word)
}
