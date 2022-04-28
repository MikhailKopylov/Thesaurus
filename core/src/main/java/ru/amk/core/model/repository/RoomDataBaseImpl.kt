package ru.amk.core.model.repository

import ru.amk.core.model.network.data.DataModel

class RoomDataBaseImpl : DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}
