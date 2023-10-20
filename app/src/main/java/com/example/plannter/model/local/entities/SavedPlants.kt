package com.example.plannter.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedPlants (
    val name:String,
    val desc:String,
    val image:ByteArray,
    @PrimaryKey val id:Int

)

