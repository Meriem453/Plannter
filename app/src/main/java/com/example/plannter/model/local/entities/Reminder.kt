package com.example.plannter.model.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    foreignKeys = [ForeignKey(
        entity = Local_plant::class,
        parentColumns = ["plantId"],
        childColumns = ["plantId"],
        onDelete = ForeignKey.CASCADE
    )]
)

data class Reminder(
    val date:String,
    val time:String,
    val plantId:Int,
    val desc:String,
    @PrimaryKey(autoGenerate = true) val id:Int
)
data class PlantWithReminders(
    @Embedded val parent: Local_plant,
    @Relation(
        parentColumn = "plantId",
        entityColumn = "plantId"
    )
    val children: List<Reminder>
)
