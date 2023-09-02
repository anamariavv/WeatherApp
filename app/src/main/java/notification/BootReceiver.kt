package notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {
	private var notificationScheduler = NotificationScheduler.getInstance()

	override fun onReceive(context: Context?, intent: Intent?) {
		if (intent?.action == "android.intent.action.BOOT_COMPLETED" && context != null) {
			notificationScheduler.startReminder(context)
		}
	}
}