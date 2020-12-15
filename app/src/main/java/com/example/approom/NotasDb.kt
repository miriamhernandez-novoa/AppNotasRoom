package com.example.approom

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Nota::class],
    version = 1
)
abstract class NotasDb:RoomDatabase() {
    abstract fun notaDao():NotaDao
}