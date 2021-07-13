package com.example.ktbook802.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class History {
    @PrimaryKey val uid: Int?,
    @ColumnInfo(name="keyword") val keyword = String
}