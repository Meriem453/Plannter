package com.example.plannter.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("nurturing")
data class nurturing(
    val name:String,
    val kind:String,
    val time:String,
    val description:String,
    @PrimaryKey val id:Int
)
