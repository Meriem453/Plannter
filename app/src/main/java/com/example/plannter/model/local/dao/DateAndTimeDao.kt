package com.example.plannter.model.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.plannter.model.local.entities.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface DateAndTimeDao {
    @Insert
    suspend fun insertTiming(reminder:Reminder)

    @Delete
    suspend fun deleteTinming(timing:Reminder)

    @Query("SELECT * FROM reminders WHERE plantId =:plantId")
    fun getTimingOfPlant(plantId:Int):Flow<List<Reminder>>

    @Query("DELETE FROM reminders WHERE plantId =:plantId")
    suspend fun deletePlantReminders(plantId: Int)

    @Query("DELETE FROM reminders WHERE id=:id")
     fun deleteReminderById(id:Int)


}