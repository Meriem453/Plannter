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

    @Query("SELECT * FROM reminder WHERE plantId =:plantId")
    fun getTimingOfPlant(plantId:Int):Flow<List<Reminder>>

    @Query("DELETE FROM reminder WHERE plantId =:plantId")
    suspend fun deletePlantReminders(plantId: Int)

    @Query("DELETE FROM reminder WHERE id=:id")
     fun deleteReminderById(id:Int)

     @Query("SELECT * FROM reminder WHERE date=:date")
     fun getReminderByDay(date:String):Flow<List<Reminder>>

     @Query("SELECT * FROM REMINDER ")
     fun getAllReminders():Flow<List<Reminder>>

}