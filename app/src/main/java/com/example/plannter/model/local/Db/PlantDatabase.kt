package com.example.plannter.model.local.Db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.plannter.model.local.dao.DateAndTimeDao
import com.example.plannter.model.local.dao.plants_dao
import com.example.plannter.model.local.entities.Reminder
import com.example.plannter.model.local.entities.Local_plant

@Database(entities=[Local_plant::class,Reminder::class], version = 1)
abstract class PlantDatabase() : RoomDatabase() {
    abstract fun PlantsDao():plants_dao
    abstract fun timingDao():DateAndTimeDao
}