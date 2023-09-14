package com.example.plannter.features.catalogue.ui


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plannter.model.remote.data.PlantDetails.PlantDetails
import com.example.plannter.model.remote.data.PlantsList.Data
import com.example.plannter.model.remote.data.PlantsList.PlantsList
import com.example.plannter.model.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RemotePlantsViewModel @Inject constructor(
    private val repo:Repository
) :ViewModel() {

    var plantsList:PlantsList? by mutableStateOf(null)


    var watering by mutableIntStateOf(0)

    var page by mutableIntStateOf(0)

    var filteredPlants:List<Data> by mutableStateOf(emptyList())

    var status by mutableStateOf("loading")
    var error by mutableStateOf("")
    var plantDetails:PlantDetails? by mutableStateOf(null)





    fun getAllPlants(){
        viewModelScope.launch {
            status="loading"
            var water = when (watering){
                0 -> { "frequent"}
                1 -> { "average"}
                2->{ "minimum"}
                else -> {""}
            }
            try {
                val response = repo.getApiPlants(page,water)
                plantsList = PlantsList(page,if(plantsList!=null) plantsList!!.data + response.body()!!.data else response.body()!!.data)
                status="success"
                updateSearch("")
                page++
            }catch (e:Exception){
                status="error"
                error= e.localizedMessage?.toString() ?: "Error"
            }


        }

    }
    fun updateSearch(search:String){

        if(plantsList!=null){
            filteredPlants =if(search=="") plantsList!!.data else plantsList!!.data.filter { it.common_name.contains(search,ignoreCase = true) }

        }
    }

    fun getPlantDetails(id:Int){
        viewModelScope.launch {
            try {
                plantDetails = repo.getApiPlantDetails(id).body()

            }catch (e:Exception){

                error= e.localizedMessage?.toString() ?: "error"
            }
        }
    }

}