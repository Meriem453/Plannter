package com.example.plannter.features.catalogue

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plannter.R
import com.example.plannter.features.my_plants.ui.CardContent
import com.example.plannter.features.my_plants.ui.CardContent2
import com.example.plannter.features.my_plants.ui.TopBar
import com.example.plannter.model.local.entities.Local_plant
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun cat_plant_details(plant:Local_plant) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {

        Image(painter = painterResource(id = R.drawable.eco_saving) //BitmapPainter(BitmapFactory.decodeByteArray(plant.image,0,plant.image.size).asImageBitmap() ) ,
            ,contentDescription ="",
            Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(200.dp)
                )
                .shadow(2.dp)
                .padding(30.dp)
        )
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            Icon(imageVector = Icons.Filled.AccountCircle, contentDescription ="", modifier = Modifier.fillMaxSize(0.1f).padding(end = 20.dp) )
            Icon(imageVector = Icons.Default.AccountBox, contentDescription ="", modifier = Modifier.fillMaxSize(0.1f) .padding(end = 20.dp) )

        }
        Card(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp), border = BorderStroke(1.dp, Color.LightGray), modifier = Modifier.weight(1f)) {
            CardContent3(plant)
        }

    }
}
@Composable
fun CardContent3(plant:Local_plant) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())) {

        Text(
            text = "Geranium",
            fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
            fontSize = 18.sp,
            modifier = Modifier
                .padding(
                    start = 30.dp,
                    top = 20.dp,
                )
                .fillMaxHeight()

        )
        LazyRow(modifier = Modifier.padding(start = 30.dp, top = 10.dp), content = {
            items(plant.classification.split(" ").size){
                Card (modifier = Modifier
                    .padding(2.dp)
                    .fillMaxSize(), shape = RoundedCornerShape(10.dp), border = BorderStroke(0.5.dp, Color.LightGray)){
                    Row ( modifier = Modifier.background(
                        colorResource(id = R.color.light_blue)
                    )){
                        Text(text = plant.classification.split(" ")[it],
                            fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
                            fontSize = 14.sp,
                            fontWeight= FontWeight.Bold,
                            color = colorResource(id = R.color.blue)
                            ,
                            modifier = Modifier.padding(5.dp)

                        )
                    }
                }
            }

        })
        Text(
            text = "editttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttthhhhhhhhhhhhhhhhhhhhhh",
            fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier
                .padding(
                    start = 30.dp,
                    top = 20.dp,
                    end = 30.dp
                )
                .fillMaxHeight()

        )


    }
}
