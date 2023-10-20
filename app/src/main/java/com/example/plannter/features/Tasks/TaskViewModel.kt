package com.example.plannter.features.Tasks

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plannter.model.local.entities.Local_plant
import com.example.plannter.model.local.entities.Reminder
import com.example.plannter.model.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val rep:Repository
):ViewModel() {

    var tasks by mutableStateOf(emptyList<Reminder>())
    //var plants by mutableStateOf(emptyList<Local_plant>())


    fun getTasks(){

      var week= mutableListOf(0,0,0,0,0,0,0)
        week[LocalDate.now().dayOfWeek.value] = 1
Log.d("DATE","NEW DATE:"+week.joinToString(""))
        viewModelScope.launch {
            rep.getReminderByDay(week.joinToString("")).collect{
                tasks = it
                //updatePlants()
            }


        }
    }

    private fun updatePlants() {
        viewModelScope.launch {
            tasks.forEach {reminder ->
                rep.getPlantById(reminder.plantId).collect{plant ->
                    //plants += plant

                }
            }
        }

    }

    fun getPlantImage(bytes:ByteArray): Bitmap {

        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }
    fun getPlant(id:Int):Flow<Local_plant>{
        return rep.getPlantById(id)
    }

    init {
        getTasks()
    }
}