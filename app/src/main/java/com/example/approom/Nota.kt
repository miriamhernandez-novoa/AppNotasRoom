package com.example.approom

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Nota(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val titulo:String,
    val contenido:String

    )