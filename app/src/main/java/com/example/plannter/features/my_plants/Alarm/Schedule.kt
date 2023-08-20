package com.example.plannter.features.my_plants.Alarm

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import java.time.LocalDateTime

class Schedule (private val context:Application) {

    private val alarmManager = context.getSystemService(AlarmManager::class.java) as AlarmManager

    suspend fun schedule(time:Long,message:String){
        val intent = Intent(context, AlarmBroadCast::class.java).apply {
            putExtra("EXTRA_MESSAGE", message)
            putExtra("EXTRA_REM_ID",time)
        }
        alarmManager.setAndAllowWhileIdle(
            /* type = */ AlarmManager.RTC_WAKEUP,
            /* triggerAtMillis = */ time,
            /* operation = */ PendingIntent.getBroadcast(
                /* context = */ context,
                /* requestCode = */ time.toInt(),
                /* intent = */ intent,
                /* flags = */ PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )

    }
    suspend fun scheduleRepeating(time:Long,message:String){
        val intent = Intent(context, AlarmBroadCast::class.java).apply {
            val b=Bundle()
            b.putString("EXTRA_MESSAGE", message)
         b.putLong("EXTRA_REM_ID",time)
            putExtra("b",b)
        }
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            time,
            AlarmManager.INTERVAL_DAY * 7,
            PendingIntent.getBroadcast(
                context,
                time.toInt(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )


    }

    fun cancel(time:Int) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                /* context = */ context,
                /* requestCode = */ time,
                /* intent = */ Intent(context, AlarmBroadCast::class.java),
                /* flags = */ PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

}