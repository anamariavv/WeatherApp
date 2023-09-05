package notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.weatherapp.R
import config.Config
import dagger.hilt.android.AndroidEntryPoint
import ui.main.MainActivity
import javax.inject.Inject


@AndroidEntryPoint
class ScheduledNotificationReceiver : BroadcastReceiver() {
	@Inject
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
		val contentIntent = Intent(applicationContext, MainActivity::class.java).setFlags(
			Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
		)
		val pendingIntent = PendingIntent.getActivity(
			applicationContext,
			NotificationScheduler.REMINDER_NOTIFICATION_REQUEST_CODE,
			contentIntent,
			flags
		)
		val builder = NotificationCompat.Builder(applicationContext, Config.notificationChannelId)
			.setContentTitle(ContextCompat.getString(applicationContext, R.string.notification_title))
			.setContentText(ContextCompat.getString(applicationContext, R.string.notification_text))
			.setSmallIcon(R.drawable.sunny)
			.setContentIntent(pendingIntent)

		notify(NotificationScheduler.REMINDER_NOTIFICATION_REQUEST_CODE, builder.build())
	}
}