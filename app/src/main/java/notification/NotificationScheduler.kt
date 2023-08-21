package notification

import android.content.Context

interface NotificationScheduler {

	fun startReminder(context: Context)

	fun stopReminder(context: Context)
}