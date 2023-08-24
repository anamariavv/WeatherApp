package ui.weekly.model

import com.example.weatherapp.R
import ui.common.model.Message

sealed class WeeklyScreenMessages : Message {

	object GetWeeklyForecastError : WeeklyScreenMessages() {

		override fun getTitleId(): Int {
			return R.string.weekly_screen_forecast_error_title
		}

		override fun getMessageId(): Int {
			return R.string.weekly_screen_forecast_error_message
		}
	}
}
