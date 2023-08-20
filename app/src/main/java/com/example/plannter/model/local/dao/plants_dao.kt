package com.example.plannter.model.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.plannter.model.local.entities.Local_plant
import kotlinx.coroutines.flow.Flow

@Dao
interface plants_dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert_plant(plant:Local_plant)

    @Delete
    suspend fun delete_plant(plant: Local_plant)

    @Query("SELECT * FROM local_plants")
    fun getAllPlants():Flow<List<Local_plant>>

    @Query("SELECT * FROM local_plants WHERE id =:id")
    fun getPlantById(id:Int):Flow<Local_plant>
}