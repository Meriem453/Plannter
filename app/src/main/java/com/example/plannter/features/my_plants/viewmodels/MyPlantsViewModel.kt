package com.example.plannter.features.my_plants.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plannter.model.local.entities.Local_plant
import com.example.plannter.model.local.entities.SavedPlants
import com.example.plannter.model.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPlantsViewModel @Inject constructor(
    private val repo:Repository
):ViewModel() {
var allPlants by mutableStateOf(listOf<Local_plant>())
    var filteredPlants by mutableStateOf(listOf<Local_plant?>())
    private set

    var allSavedPlants by mutableStateOf(listOf<SavedPlants>())
    var filteredSavedPlants by mutableStateOf(listOf<SavedPlants>())

    var search by mutableStateOf("")

fun getAllPlants(){
    viewModelScope.launch {
        repo.getAllPlants().collect{plants->
            allPlants=plants
            updateSearch()

        }
    }
}
    fun getAllSavedPlants(){
        viewModelScope.launch {
            repo.getAllSavedPlants().collect{savedPlants ->
                allSavedPlants=savedPlants
                updateSearch()

            }
        }
    }
fun updateSearch(){
    filteredPlants = listOf(null) + if(search=="") allPlants else allPlants.filter { it.name.contains(search,ignoreCase = true) }
    filteredSavedPlants=if(search=="") allSavedPlants else allSavedPlants.filter { it.name.contains(search,ignoreCase = true) }
}




}