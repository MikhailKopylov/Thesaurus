package ru.amk.tesaurus.model.dataSource

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_request")
data class RoomHistory(

    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "search_word") val searchWord: String,
)
