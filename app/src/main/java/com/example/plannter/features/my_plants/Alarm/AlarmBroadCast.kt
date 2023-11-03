package com.example.plannter.features.my_plants.Alarm


import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.plannter.R
import com.example.plannter.features.destinations.AlarmScreenDestination
import com.example.plannter.model.repository.Repository
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class AlarmBroadCast :BroadcastReceiver() {
@Inject
lateinit var repo: Repository
    override fun onReceive(context: Context?, intent: Intent?) {
val b = intent?.getBundleExtra("b")

        val message = b?.getString("EXTRA_MESSAGE") ?: return
        val id = b.getLong("EXTRA_REM_ID")

val manager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(context, "channel")
            .setSmallIcon(R.drawable.logo)
            .setContentTitle("Yooho! don't forget your plant")
            .setContentText(message).setPriority(Notification.PRIORITY_MAX)
            .build()

manager.notify(1,notification)

            repo.deleteReminderById(id.toInt())



    }
}