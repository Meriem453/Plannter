package com.example.plannter.features.catalogue.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.plannter.R
import com.example.plannter.model.remote.data.PlantDetails.PlantDetails
import com.ramcosta.composedestinations.annotation.Destination



@OptIn(ExperimentalGlideComposeApi::class)
@Destination
@Composable
fun Cat_plant_details( plantDetails: PlantDetails ,image:String) {
    Column(Modifier.fillMaxSize()) {

        GlideImage(
                    model = image
            , contentDescription = "",
            Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth()

                .shadow(2.dp)
              ,contentScale = ContentScale.Crop
        )

        Card(

            border = BorderStroke(1.dp, Color.LightGray),
           modifier = Modifier
               .fillMaxHeight(0.7f)
               .weight(1f)
        ) {
            CardContent3(plantDetails)
        }

    }
}
@Composable
fun CardContent3(plantDetails: PlantDetails) {

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
        , verticalArrangement = Arrangement.Top
    ) {



        Text(
            text = plantDetails.common_name,
            fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
            fontSize = 18.sp,
            color = colorResource(id = R.color.title_green),
            modifier = Modifier
                .padding(
                    start = 30.dp,
                    top = 30.dp,
                )
                .fillMaxHeight()

        )
        Text(
            text =plantDetails.description,
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
