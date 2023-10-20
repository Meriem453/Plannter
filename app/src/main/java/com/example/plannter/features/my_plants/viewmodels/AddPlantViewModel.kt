package com.example.plannter.features.my_plants.viewmodels

import android.app.Application
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.TransactionTooLargeException
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plannter.features.my_plants.Alarm.Schedule
import com.example.plannter.model.local.entities.Reminder
import com.example.plannter.model.local.entities.Local_plant
import com.example.plannter.model.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AddPlantViewModel @Inject constructor(
    private val repo: Repository,
    private val alarm : Schedule,
    private val c:Application
) : ViewModel() {
    var plant by mutableStateOf(Local_plant(null,"","","","",0))

var days:List<Boolean> by mutableStateOf(
    listOf(
        false,
        false,
        false,
        false,
        false,
        false,
        false
    )
)
var init by mutableStateOf(false)

    var imageUri by
        mutableStateOf<Uri?>(null)





 var saved by mutableStateOf(false)

    var timingDesc by mutableStateOf("")

    var checkedRepeat by mutableStateOf(false)

    var pickedTime by
        mutableStateOf(LocalTime.now())

    val formattedTime by
        derivedStateOf {
            DateTimeFormatter.ofPattern("hh:mm").format(pickedTime)

    }
    var showDialog by
        mutableStateOf(false)




    var reminders by mutableStateOf(listOf<Reminder>())

fun getReminders(plantId:Int){
    viewModelScope.launch {
        repo.getTimingOfPlant(plantId).collect{
            reminders = it
        }
    }
}

    fun insertPlant(){

if(plantInfoCheck()){
    viewModelScope.launch {
    repo.insertPlant(plant)
    saved=true

}

            }
        }


    private fun plantInfoCheck(): Boolean {
var valid =  true
        if(plant.name==""){
            Toast.makeText(c,"Enter a name",Toast.LENGTH_SHORT).show()
            valid=false
        }
        if(plant.place==""){
            Toast.makeText(c,"Enter a place",Toast.LENGTH_SHORT).show()
            valid=false
        }
        if(plant.classification==""){
            Toast.makeText(c,"Enter a classification",Toast.LENGTH_SHORT).show()
            valid=false
        }
        if(plant.description==""){
            Toast.makeText(c,"Enter a description",Toast.LENGTH_SHORT).show()
            valid=false
        }
        if(plant.image==null){
            Toast.makeText(c,"Pick an image",Toast.LENGTH_SHORT).show()
            valid=false
        }
        return valid
    }

    private val currentTime = Calendar.getInstance()
    fun sceduleAlarm( ) {

        viewModelScope.launch {
            val calendar = Calendar.getInstance()
            days.forEachIndexed { position, day ->
                if (day) {
                    calendar.set(Calendar.DAY_OF_WEEK, position + 1)
                    calendar.set(Calendar.HOUR_OF_DAY, pickedTime.hour)
                    calendar.set(Calendar.MINUTE, pickedTime.minute)
                    calendar.set(Calendar.SECOND, 0)


                    if (currentTime.after(calendar)) {
                        calendar.add(Calendar.WEEK_OF_YEAR, 1)
                    }

                if (!checkedRepeat) {
                    alarm.schedule(
                        calendar.timeInMillis,
                        "$timingDesc :${plant.name}"
                    )
                } else {
                    alarm.scheduleRepeating(
                        calendar.timeInMillis,
                        "It's $formattedTime\n$timingDesc ${plant.name}"
                    )
                }
                    Toast.makeText(c,
                        "Reminder saved ${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)} ${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH)}/${calendar.get(Calendar.YEAR)}",
                        Toast.LENGTH_LONG).show()

            }
        }
            var date = ""
            days.forEach {
                date += if (it) "1" else "0"
            }
            repo.insertTiming(
                Reminder(
                    date,
                    formattedTime,
                    plant.plantId,
                    timingDesc,
                    calendar.timeInMillis.toInt()
                )
            )
    }

    }

    fun updateBitmap() {
       val bitmap= if (Build.VERSION.SDK_INT < 28) {
           try {MediaStore.Images
                .Media.getBitmap(c.contentResolver,imageUri)
           }catch (e:TransactionTooLargeException){
               Toast.makeText(c,"Pick another image",Toast.LENGTH_LONG).show()
               null
           }
        } else {
                val source = ImageDecoder
                    .createSource(c.contentResolver,imageUri!!)
                ImageDecoder.decodeBitmap(source)



        }
        val stream = ByteArrayOutputStream()
        bitmap!!.compress(Bitmap.CompressFormat.PNG, 100, stream)

        plant = Local_plant(
            stream.toByteArray(),
            name = plant.name,
            place = plant.place,
            classification = plant.classification,
            description = plant.description,
            plantId = plant.plantId
        )
            stream.close()




    }

    fun deletePlant(plant:Local_plant) {
        viewModelScope.launch {
            repo.deletePlant(plant)
              //repo.deletePlantReminders(plant.plantId)
        }
    }

    fun deleteReminder(reminder: Reminder){
        viewModelScope.launch {
            repo.deleteTiming(reminder)
            alarm.cancel(reminder.id)
        }
    }
}