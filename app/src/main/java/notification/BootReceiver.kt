package notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import javax.inject.Inject

class BootReceiver : BroadcastReceiver() {
	@Inject
	lateinit var notificationScheduler: NotificationScheduler

	override fun onReceive(context: Context?, intent: Intent?) {
		if (intent?.action == "android.intent.action.BOOT_COMPLETED" && context != null) {
			notificationScheduler.startReminder(context)
		}
	}
}