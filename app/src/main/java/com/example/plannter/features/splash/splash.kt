package com.example.plannter.features.splash

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example.plannter.R
import com.example.plannter.features.destinations.MyPlantsMainDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun Splash(naviagtor: DestinationsNavigator){
    var navigate by remember {
        mutableStateOf(false)
    }
    Box (
        modifier = Modifier
            .background(colorResource(id = R.color.background_green))
            .fillMaxSize()
    ){
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "", modifier = Modifier
            .align(
                Alignment.Center
            )
            .fillMaxSize(0.2f))

        Handler(Looper.getMainLooper()).postDelayed({
                navigate=true
        }, 3000)
        if(navigate) naviagtor.navigate(MyPlantsMainDestination())

    }
}