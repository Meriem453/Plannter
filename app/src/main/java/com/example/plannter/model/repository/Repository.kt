package com.example.plannter.model.repository

import com.example.plannter.model.local.Db.PlantDatabase
import com.example.plannter.model.local.entities.Reminder
import com.example.plannter.model.local.entities.Local_plant
import kotlinx.coroutines.flow.Flow

class Repository(private val db:PlantDatabase) {
   private val plantsDao=db.PlantsDao()
    private val timingDao = db.timingDao()
   suspend fun insertPlant(plant:Local_plant){
    plantsDao.insert_plant(plant)
    }
    suspend fun deletePlant(plant: Local_plant){
        plantsDao.delete_plant(plant)
    }
    fun getPlantById(id:Int):Flow<Local_plant>{
        return plantsDao.getPlantById(id)
    }

    fun getAllPlants():Flow<List<Local_plant>>{
        return plantsDao.getAllPlants()
    }

    suspend fun insertTiming(timing:Reminder){
        timingDao.insertTiming(timing)
    }

    suspend fun deleteTiming(timing: Reminder){
        timingDao.deleteTinming(timing)
    }

    fun getTimingOfPlant(plantId:Int):Flow<List<Reminder>>{
        return timingDao.getTimingOfPlant(plantId)
    }

    suspend fun deletePlantReminders(plantId: Int){
        timingDao.deletePlantReminders(plantId = plantId)
    }
     fun deleteReminderById(id: Int){
        timingDao.deleteReminderById(id)
    }
}