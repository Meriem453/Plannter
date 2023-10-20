package com.example.plannter.model.local.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("local_plants")
@kotlinx.parcelize.Parcelize
data class Local_plant(
    var image:ByteArray?,
    var name:String,
    var place:String,
    var classification: String,
    var description:String,
    @PrimaryKey val plantId:Int
):Parcelable
