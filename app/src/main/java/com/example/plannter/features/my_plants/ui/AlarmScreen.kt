package com.example.plannter.features.my_plants.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plannter.R

@com.ramcosta.composedestinations.annotation.Destination
@Composable
fun AlarmScreen(message:String){
    Column(verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
           modifier = Modifier
               .fillMaxSize()
               .background(colorResource(id = R.color.background_green))
    ) {
        Icon(painter = painterResource(id = R.drawable.logo),
            contentDescription ="",
            modifier = Modifier.weight(1f) )

        Text(text = message,
            fontFamily =  FontFamily(Font(R.font.josefinsans_regular)),
            fontSize = 18.sp,
            modifier = Modifier
                .padding(
                    start = 15.dp,
                    top = 15.dp,
                    end = 15.dp,
                    bottom = 50.dp
                )
        )
    }
}