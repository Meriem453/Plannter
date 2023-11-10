package com.example.plannter.features.my_plants.ui

import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.plannter.R
import com.example.plannter.features.destinations.AddPlantScreenDestination
import com.example.plannter.features.destinations.MyPlantsMainDestination
import com.example.plannter.features.my_plants.viewmodels.AddPlantViewModel
import com.example.plannter.model.local.entities.Local_plant
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun show_details(plant:Local_plant,navigator: DestinationsNavigator){

    val viewModel= hiltViewModel<AddPlantViewModel>()
    val context = LocalContext.current
   var dialogState by remember {
           mutableStateOf(false)
   }

    Column(Modifier.fillMaxSize().background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween) {
        TopBar("Plant details")
        Image(
            bitmap = BitmapFactory.decodeByteArray(plant.image,0,plant.image!!.size).asImageBitmap(),
            contentScale = ContentScale.Crop,
            contentDescription ="",
            modifier =
            Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth()
                .shadow(2.dp)

        )
        Row(horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()) {
            Icon(painter = painterResource(id = R.drawable.edit),
                contentDescription ="",
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp)
                    .padding(end = 20.dp)
                    .clickable {

                        navigator.navigate(AddPlantScreenDestination(plant))
                    },
                tint = colorResource(id = R.color.background_green)
            )
            Icon(painter = painterResource(id = R.drawable.delete),
                contentDescription ="",
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp)
                    .padding(end = 20.dp)
                    .clickable {
                        dialogState = true
                    },
                tint = colorResource(id = R.color.background_green)
                )

        }
        Card(
            shape = RoundedCornerShape(topStart = 20.dp,
                topEnd = 20.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            modifier = Modifier
                .weight(1f)) {
            CardContent(plant,viewModel)


        }

    }

    if(dialogState){
        Dialog(onDismissRequest = { dialogState = false }) {
            Column(modifier = Modifier
                .background(Color.White)
                )
                 {

                Text(text = "Delete plant",
                    fontFamily =  FontFamily(Font(R.font.josefinsans_regular)),
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.title_green),
                    modifier = Modifier
                        .padding(
                            start = 15.dp,
                            top = 15.dp,
                        )
                        .align(Alignment.Start)


                )
                Text(text = "Are you sure you want to delete this plant ?",
                    fontFamily =  FontFamily(Font(R.font.josefinsans_regular)),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(
                            start = 15.dp,
                            top = 15.dp,
                        )
                )
                Row(modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 15.dp)) {
                    Text(text = "Keep",
                        fontFamily =  FontFamily(Font(R.font.josefinsans_regular)),
                        fontSize = 16.sp,
                        color = Color.LightGray,
                        modifier = Modifier
                            .padding(
                                end = 15.dp,
                                top = 15.dp,
                            )
                            .clickable {
                                dialogState = false
                            }
                    )

                    Text(text = "Delete",
                        fontFamily =  FontFamily(Font(R.font.josefinsans_regular)),
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.title_green),
                        modifier = Modifier
                            .padding(
                                end = 15.dp,
                                top = 15.dp,
                            )
                            .clickable {
                                viewModel.deletePlant(plant)
                                Toast
                                    .makeText(context, "Plant deleted", Toast.LENGTH_SHORT)
                                    .show()
                                navigator.navigate(MyPlantsMainDestination())
                            }
                    )
                }

        }
    }
}}





@Composable
fun CardContent(plant:Local_plant,viewModel: AddPlantViewModel) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = plant.name,
                fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
                fontSize = 18.sp,
                color = colorResource(id = R.color.title_green),
                modifier = Modifier
                    .padding(
                        start = 30.dp,
                        top = 20.dp,
                    )
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)

            )
            Text(
                text = plant.place,
                fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.selected_item_color),
                modifier = Modifier
                    .padding(
                        end = 30.dp,
                        top = 20.dp,
                    )
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
            )


        }
        Text(
            text = plant.description,
            fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
            fontSize = 17.sp,
            modifier = Modifier
                .padding(
                    start = 30.dp,
                    top = 20.dp,
                )
                .fillMaxHeight()

        )
        Text(
            text = "Classifications:",
            fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
            fontSize = 18.sp,
            color = colorResource(id = R.color.selected_item_color),
            modifier = Modifier
                .padding(
                    start = 30.dp,
                    top = 60.dp,
                )
                .fillMaxHeight()

        )
        LazyRow(
            modifier = Modifier.padding(start = 30.dp, top = 10.dp),
            content = {
            items(plant.classification.split(" ").size){
                Card (modifier = Modifier
                    .padding(2.dp)
                    .fillMaxSize(),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(0.5.dp, Color.LightGray)){
                    Row ( modifier = Modifier.background(
                        colorResource(id = R.color.background_green))){
                        Text(text = plant.classification.split(" ")[it],
                            fontFamily = FontFamily(Font(R.font.josefinsans_light)),
                            fontSize = 14.sp,
                            color = colorResource(id = R.color.title_green),
                            modifier = Modifier.padding(5.dp)

                        )
                    }
                }
            }

        })


        Text(text = "Reminders",color = colorResource(id = R.color.title_green), fontFamily = FontFamily(Font(R.font.josefinsans_regular)), fontSize = 18.sp, modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 10.dp))
        viewModel.getReminders(plant.plantId)
        if(viewModel.reminders.isEmpty()){
            Text(text = "There are no reminders", color = Color.LightGray, fontFamily = FontFamily(Font(R.font.josefinsans_regular)), fontSize = 14.sp, modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 10.dp))

        }else{
            viewModel.reminders.forEachIndexed { index, dateAndTime ->
                Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, top = 10.dp)
                    .fillMaxWidth()){
                    Text(text =dateAndTime.time, fontFamily = FontFamily(Font(R.font.josefinsans_regular)), fontSize = 18.sp, modifier = Modifier.padding(start = 10.dp, end = 10.dp))
                    Card (modifier = Modifier.fillMaxWidth(0.7f), border = BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(20.dp)){
                        Column (modifier = Modifier
                            .background(Color.White)
                            .fillMaxSize()){
                            Row( modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)){
                                dateAndTime.date.forEachIndexed { index, c ->
                                    Icon(imageVector = Icons.Filled.Notifications, contentDescription = "", modifier = Modifier.height(20.dp),
                                        tint = colorResource(id = if (c =='1') R.color.background_green else R.color.orange)
                                        )
                                }
                            }
                            Text(text =dateAndTime.desc, color = colorResource(id = R.color.title_green), fontFamily = FontFamily(Font(R.font.josefinsans_regular)), fontSize = 16.sp, modifier = Modifier.padding(10.dp))

                        }
                    }

                }

            }
        }




    }
}
