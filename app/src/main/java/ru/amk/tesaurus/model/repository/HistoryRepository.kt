package ru.amk.tesaurus.model.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.amk.tesaurus.model.dataSource.DatabaseHistory
import ru.amk.tesaurus.model.dataSource.RoomHistory

class HistoryRepository(
    private val databaseHistory: DatabaseHistory
) : DataSourceHistory<List<RoomHistory>> {

    override suspend fun getData(): List<RoomHistory> {
        return withContext(Dispatchers.IO) { databaseHistory.wordDao().getAllSearchWords() }
    }

    override suspend fun saveData(word: String) {
        withContext(Dispatchers.IO) {
            databaseHistory.wordDao().addSearchWord(word = RoomHistory(searchWord = word))
        }
    }
}
