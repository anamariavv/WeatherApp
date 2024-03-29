package notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*
import javax.inject.Inject

class NotificationScheduler @Inject constructor() {
	companion object {
		private const val time = "08:00"
		const val REMINDER_NOTIFICATION_REQUEST_CODE = 1
		private const val REMINDER_TIME = time
		private const val flags = PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
	}

	fun startReminder(context: Context) {
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

		if (Calendar.getInstance(Locale.ENGLISH).apply { add(Calendar.MINUTE, 1) }.timeInMillis - calendar.timeInMillis > 0) {
			calendar.add(Calendar.DATE, 1)
		}

		alarmManager.setAlarmClock(
			AlarmManager.AlarmClockInfo(calendar.timeInMillis, intent),
			intent
		)
	}
}