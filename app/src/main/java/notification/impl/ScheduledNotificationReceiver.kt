package notification.impl

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.weatherapp.R
import dagger.hilt.android.AndroidEntryPoint
import ui.main.MainActivity

@AndroidEntryPoint
class ScheduledNotificationReceiver : BroadcastReceiver() {
	lateinit var notificationScheduler: NotificationScheduler

	private val flags = PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT

	override fun onReceive(context: Context, intent: Intent?) {
		val notificationManager = context.getSystemService(NotificationManager::class.java)

		notificationManager.sendReminderNotification(
			applicationContext = context,
		)

		notificationScheduler.startReminder(context)
	}

	private fun NotificationManager.sendReminderNotification(applicationContext: Context) {
		val contentIntent = Intent(applicationContext, MainActivity::class.java)
		val pendingIntent = PendingIntent.getActivity(
			applicationContext,
			1,
			contentIntent,
			flags
		)
		val builder = NotificationCompat.Builder(applicationContext, "forecastNotificationChannel")
			.setContentTitle("Forecast")
			.setContentText("Test title")
			.setSmallIcon(R.drawable.sunny)
			.setContentIntent(pendingIntent)
			.setAutoCancel(true)

		notify(1, builder.build())
	}
}