package ru.amk.tesaurus.model.repository

import ru.amk.tesaurus.model.network.data.DataModel

class RoomDataBaseImpl : DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}
