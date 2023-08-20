package com.example.plannter.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity("reminders")
data class Reminder(
    val date:String,
    val time:String,
    val plantId:Int,
    val desc:String,
    @PrimaryKey(autoGenerate = true) val id:Int
)
