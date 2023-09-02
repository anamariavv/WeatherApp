package app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import config.Config
import dagger.hilt.android.HiltAndroidApp
import notification.NotificationScheduler

@HiltAndroidApp
class BaseApplication : Application() {
	private var notificationScheduler = NotificationScheduler.getInstance()

	override fun onCreate() {
		super.onCreate()
		createNotificationChannel()
		notificationScheduler.startReminder(this)
	}

	private fun createNotificationChannel() {
		val notificationChannel = NotificationChannel(Config.notificationChannelId, Config.notificationChannelName, NotificationManager.IMPORTANCE_LOW)
		notificationChannel.description = Config.notificationChannelDescription

		val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
		notificationManager.createNotificationChannel(notificationChannel)
	}
}