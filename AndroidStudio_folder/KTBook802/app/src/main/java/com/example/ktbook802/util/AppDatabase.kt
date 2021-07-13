package com.example.ktbook802.util

import androidx.room.Database
import com.example.ktbook802.model.History

@Database(entities = [History])
abstract class AppDatabase {
    abstract fun historyDao():HistoryDao
}