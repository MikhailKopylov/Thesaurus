package ru.amk.core.model.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSearchWord(word: RoomHistory)

    @Query("SELECT * FROM history_request")
    fun getAllSearchWords(): List<RoomHistory>
}
