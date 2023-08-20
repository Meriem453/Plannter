package com.example.plannter.features.my_plants.data

import androidx.annotation.DrawableRes

data class BarItem (val label:String,
    val isSelected:Boolean,
    @DrawableRes val icon:Int
    )