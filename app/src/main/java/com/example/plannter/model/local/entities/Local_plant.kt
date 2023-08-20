package com.example.plannter.model.local.entities

import android.os.Parcelable
import androidx.navigation.NavType
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity("local_plants")
@kotlinx.parcelize.Parcelize
data class Local_plant(
    var image:ByteArray?,
    var name:String,
    var place:String,
    var classification: String,
    var description:String,
    @PrimaryKey val id:Int
):Parcelable
