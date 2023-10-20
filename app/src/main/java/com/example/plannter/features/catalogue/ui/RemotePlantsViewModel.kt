package com.example.plannter.features.catalogue.ui


import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.TransactionTooLargeException
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plannter.model.local.entities.Local_plant
import com.example.plannter.model.local.entities.SavedPlants
import com.example.plannter.model.remote.data.PlantDetails.PlantDetails
import com.example.plannter.model.remote.data.PlantsList.Data
import com.example.plannter.model.remote.data.PlantsList.PlantsList
import com.example.plannter.model.repository.Repository
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import kotlin.random.Random

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
fun savePlant(plant: Data,c:Context){

        Picasso.get().load(plant.default_image.original_url).into(object : Target{
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {

                viewModelScope.launch {
                    val stream = ByteArrayOutputStream()
                    bitmap!!.compress(Bitmap.CompressFormat.PNG, 100, stream)
                    repo.savePlant(SavedPlants(
                        plant.common_name,
                        plant.cycle,
                        stream.toByteArray(),
                        plant.id
                    ))
                    stream.close()

                }
                Toast.makeText(c,"Plant saved!",Toast.LENGTH_SHORT).show()
            }

            override fun onBitmapFailed(e: java.lang.Exception?, errorDrawable: Drawable?) {
                Toast.makeText(c,"Failed to save, check your connection!",Toast.LENGTH_SHORT).show()
            }
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                Toast.makeText(c,"Saving",Toast.LENGTH_SHORT).show()
            }

        })



}
init {
    getAllPlants()
}

}