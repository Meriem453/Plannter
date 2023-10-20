package com.example.plannter.model.remote

import com.example.plannter.model.remote.data.PlantDetails.PlantDetails
import com.example.plannter.model.remote.data.PlantsList.PlantsList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PerenuelApi {
    @GET("species-list?key=sk-bLVC65294f8f550622448")
    suspend fun getAllPlants(
        @Query("page") page:Int,
        @Query("watering") watering:String)
    :Response<PlantsList>

    @GET("species/details/{id}?key=sk-bLVC65294f8f550622448")
    suspend fun getPlantDetails(
        @Path("id") id:Int
    ):Response<PlantDetails>

}