package app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import dagger.hilt.android.HiltAndroidApp
import notification.impl.NotificationScheduler

@HiltAndroidApp
class BaseApplication : Application() {
	var notificationScheduler = NotificationScheduler.getInstance()

	override fun onCreate() {
		super.onCreate()
		createNotificationChannel()
		notificationScheduler.startReminder(this)
	}

	private fun createNotificationChannel() {
		val notificationChannel = NotificationChannel("forecastNotificationChannel", "forecast", NotificationManager.IMPORTANCE_LOW)
		notificationChannel.description = "A notification channel for weather updates"

		val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
		notificationManager.createNotificationChannel(notificationChannel)
	}
}