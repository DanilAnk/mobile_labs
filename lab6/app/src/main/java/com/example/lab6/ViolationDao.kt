package com.example.lab6


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ViolationDao {
    @Insert
    suspend fun insert(violation: Violation)

    @Update
    suspend fun update(violation: Violation)

    @Delete
    suspend fun delete(violation: Violation)

    @Query("SELECT * FROM violations")
    suspend fun getAllViolations(): List<Violation>

    @Query("SELECT * FROM violations WHERE id = :id")
    suspend fun getViolationById(id: Long): Violation?
}
