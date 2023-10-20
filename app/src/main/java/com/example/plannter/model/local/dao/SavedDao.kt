package com.example.plannter.model.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.plannter.model.local.entities.SavedPlants
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePlant(plants: SavedPlants)

    @Delete
    suspend fun unsavePlant(plants: SavedPlants)

    @Query("SELECT * FROM SavedPlants")
    fun getAllSavedPlants():Flow<List<SavedPlants>>
}