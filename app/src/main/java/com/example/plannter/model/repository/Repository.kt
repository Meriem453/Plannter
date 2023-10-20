package com.example.plannter.model.repository

import com.example.plannter.model.local.Db.PlantDatabase
import com.example.plannter.model.local.entities.Reminder
import com.example.plannter.model.local.entities.Local_plant
import com.example.plannter.model.local.entities.SavedPlants
import com.example.plannter.model.remote.PerenuelApi
import com.example.plannter.model.remote.data.PlantDetails.PlantDetails
import com.example.plannter.model.remote.data.PlantsList.PlantsList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class Repository(
    db:PlantDatabase,
    private val api:PerenuelApi
    ) {
   private val plantsDao=db.PlantsDao()
    private val timingDao = db.timingDao()
    private val savedPlantsDao = db.savedPlantsDao()
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

    suspend fun getApiPlants(page:Int,watering:String):Response<PlantsList>{
        return api.getAllPlants(page,watering)
    }

    suspend fun getApiPlantDetails(id:Int):Response<PlantDetails>{
        return api.getPlantDetails(id)
    }

    suspend fun savePlant(plant:SavedPlants){
        savedPlantsDao.savePlant(plant)
    }
    suspend fun unsavePlant(plant: SavedPlants){
        savedPlantsDao.unsavePlant(plant)
    }

    fun getAllSavedPlants():Flow<List<SavedPlants>>{
        return savedPlantsDao.getAllSavedPlants()
    }

    fun getReminderByDay(day:String):Flow<List<Reminder>>{
        return timingDao.getReminderByDay(day)
    }

    fun getAllReminders():Flow<List<Reminder>>{
        return timingDao.getAllReminders()
    }
}