package com.example.plannter.model.remote.data.PlantsList

data class Data(
    val common_name: String,
    val cycle: String,
    val default_image: DefaultImage,
    val id: Int,
    var fav:Boolean = false

    )