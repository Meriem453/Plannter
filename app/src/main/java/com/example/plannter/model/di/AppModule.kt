package com.example.plannter.model.di

import android.app.Application
import androidx.room.Room
import com.example.plannter.features.my_plants.Alarm.Schedule
import com.example.plannter.model.local.Db.PlantDatabase
import com.example.plannter.model.remote.PerenuelApi
import com.example.plannter.model.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun createRoomDb(c: Application):PlantDatabase = Room.databaseBuilder(c, PlantDatabase::class.java, "plant_db").build()


    @Provides
    @Singleton
    fun createRepo(db:PlantDatabase,api: PerenuelApi):Repository{
        return Repository(db,api)
    }

    @Provides
    @Singleton
    fun createSchedule(c:Application):Schedule{
        return Schedule(c)
    }

    @Provides
    @Singleton
    fun createPerenuelApi():PerenuelApi{

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://perenual.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(PerenuelApi::class.java)
    }
}