package notification.impl

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import notification.NotificationScheduler
import java.util.*
import javax.inject.Inject

class NotificationSchedulerImpl @Inject constructor(): NotificationScheduler {
	companion object {
		private const val REMINDER_NOTIFICATION_REQUEST_CODE = 1
		private const val REMINDER_TIME = "13:25"
		private const val flags = PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
	}

	override fun startReminder(context: Context) {
		val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

		val (hours, min) = REMINDER_TIME.split(":").map { it.toInt() }
		val intent = Intent(context.applicationContext, ScheduledNotificationReceiver::class.java).let { intent ->
			PendingIntent.getBroadcast(
				context.applicationContext,
				REMINDER_NOTIFICATION_REQUEST_CODE,
				intent,
				flags
			)
		}

		val calendar: Calendar = Calendar.getInstance(Locale.ENGLISH).apply {
			set(Calendar.HOUR_OF_DAY, hours)
			set(Calendar.MINUTE, min)
		}

		/*if (Calendar.getInstance(Locale.ENGLISH).apply { add(Calendar.MINUTE, 1) }.timeInMillis - calendar.timeInMillis > 0) {
			calendar.add(Calendar.DATE, 1)
		}*/

		alarmManager.setAlarmClock(
			AlarmManager.AlarmClockInfo(calendar.timeInMillis, intent),
			intent
		)
	}

	override fun stopReminder(context: Context) {
		val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

		val intent = Intent(context, ScheduledNotificationReceiver::class.java).let { intent ->
			PendingIntent.getBroadcast(
				context,
				REMINDER_NOTIFICATION_REQUEST_CODE,
				intent,
				0
			)
		}
		alarmManager.cancel(intent)
	}
}