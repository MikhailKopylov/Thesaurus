package ru.amk.tesaurus.model.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.amk.tesaurus.model.network.data.DataModel

class RepositoryImpl(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return withContext(Dispatchers.IO) { dataSource.getData(word) }
    }
}
