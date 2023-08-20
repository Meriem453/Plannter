package com.example.plannter.model.di

import android.app.Application
import androidx.room.Room
import com.example.plannter.features.my_plants.Alarm.Schedule
import com.example.plannter.model.local.Db.PlantDatabase
import com.example.plannter.model.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun createRoomDb(c: Application):PlantDatabase = Room.databaseBuilder(c, PlantDatabase::class.java, "plant_db").build()


    @Provides
    @Singleton
    fun createRepo(db:PlantDatabase):Repository{
        return Repository(db)
    }

    @Provides
    @Singleton
    fun createSchedule(c:Application):Schedule{
        return Schedule(c)
    }
}