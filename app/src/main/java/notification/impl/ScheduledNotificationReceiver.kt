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
	var notificationScheduler = NotificationScheduler.getInstance()

	private val flags = PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT

	override fun onReceive(context: Context, intent: Intent?) {
		val notificationManager = context.getSystemService(NotificationManager::class.java)

		notificationManager.sendReminderNotification(
			applicationContext = context,
		)

		notificationScheduler.startReminder(context)
	}

	private fun NotificationManager.sendReminderNotification(applicationContext: Context) {
		val contentIntent = Intent(applicationContext, MainActivity::class.java).setFlags(
			Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
		)
		val pendingIntent = PendingIntent.getActivity(
			applicationContext,
			1,
			contentIntent,
			flags
		)
		val builder = NotificationCompat.Builder(applicationContext, "forecastNotificationChannel")
			.setContentTitle("Good Morning!")
			.setContentText("Click here to view the current weather")
			.setSmallIcon(R.drawable.sunny)
			.setContentIntent(pendingIntent)

		notify(1, builder.build())
	}
}