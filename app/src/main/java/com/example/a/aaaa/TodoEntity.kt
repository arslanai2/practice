package com.example.a.aaaa

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "arslantbl")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String
)