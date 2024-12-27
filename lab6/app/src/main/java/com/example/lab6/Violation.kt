package com.example.lab6

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "violations")
data class Violation(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var title: String,
    var date: String,
    var isResolved: Boolean,
    var name: String
) : Serializable

