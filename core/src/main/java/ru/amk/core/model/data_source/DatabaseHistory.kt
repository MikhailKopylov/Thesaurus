package ru.amk.core.model.data_source

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomHistory::class], version = 1)
abstract class DatabaseHistory : RoomDatabase() {

    abstract fun wordDao(): WordDao
}
