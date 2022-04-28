package ru.amk.core.model.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.amk.core.model.data_source.DatabaseHistory
import ru.amk.core.model.data_source.RoomHistory

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
