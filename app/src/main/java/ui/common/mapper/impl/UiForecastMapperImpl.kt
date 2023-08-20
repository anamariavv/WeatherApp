package ui.common.mapper.impl

import android.icu.text.SimpleDateFormat
import com.example.weatherapp.R
import model.forecast.CurrentConditions
import model.forecast.DailyForecast
import model.forecast.Forecast
import model.forecast.HourlyForecast
import model.forecast.HourlyForecastItem
import model.forecast.Measurement
import model.forecast.Temperature
import model.forecast.Wind
import ui.common.mapper.UiForecastMapper
import ui.home.model.UiCurrentConditions
import ui.home.model.UiHourlyForecast
import ui.home.model.UiHourlyForecastItem
import ui.weekly.model.UiDailyForecast
import ui.weekly.model.UiWeeklyForecast
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class UiForecastMapperImpl : UiForecastMapper {

	override fun toWeeklyForecast(forecast: Forecast): UiWeeklyForecast {
		val items = forecast.dailyForecasts.map { toUiDailyForecast(it) }
		return UiWeeklyForecast(items)
	}

	override fun toUiHourlyForecast(forecast: HourlyForecast): UiHourlyForecast {
		val items = forecast.items.map { toUiHourlyForecastItem(it) }
		return UiHourlyForecast(items)
	}

	override fun toUiCurrentConditions(item: CurrentConditions): UiCurrentConditions {
		return UiCurrentConditions(
			isDayTime = item.isDayTime,
			mobileLink = item.mobileLink,
			hasPrecipitation = item.hasPrecipitation,
			precipitationType = item.precipitationType,
			temperature = formatTemperature(item.temperature, false),
			realFeelTemperature = formatTemperature(item.realFeelTemperature, true),
			weatherText = item.weatherText,
			weatherIconId = mapIconToId(item.weatherIcon, item.isDayTime),
			hasWind = hasWind(item.wind),
			windSpeed = formatWind(item.wind),
			hasHumidity = item.relativeHumidity != null,
			humidity = formatHumidity(item.relativeHumidity),
			hasUvIndex = item.uVIndex != null,
			uvIndex = formatUvIndex(item.uVIndex)
		)
	}

	private fun toUiDailyForecast(item: DailyForecast): UiDailyForecast {
		return UiDailyForecast(
			day = formatDay(item.epochDate),
			weatherIconId = mapIconToId(item.icon, true),
			temperature = formatMinMaxTemperature(item.temperature)
		)
	}

	private fun toUiHourlyForecastItem(item: HourlyForecastItem): UiHourlyForecastItem {
		return UiHourlyForecastItem(
			time = toTime(item.epochDateTime.toLong()),
			iconPhrase = item.iconPhrase,
			weatherIconId = mapIconToId(item.weatherIcon, item.isDaylight),
			temperature = formatTemperature(item.temperature, false)
		)
	}

	private fun toTime(timestamp: Long): String {
		val sdf = SimpleDateFormat("HH:mm", Locale.US)
		val date = Date(timestamp * 1000)
		return sdf.format(date)
	}

	private fun formatDay(timestamp: Long): String {
		val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")

		val timestampAsDateString = DateTimeFormatter.ISO_INSTANT.format(java.time.Instant.ofEpochSecond(timestamp))

		val date = LocalDate.parse(timestampAsDateString, format)
		return date.dayOfWeek.toString()
	}

	private fun formatTemperature(temperature: Measurement, isRealFeel: Boolean): String {
		return if (isRealFeel) {
			String.format("Feels like %d\u00B0 %s", temperature.value?.toInt(), temperature.unit)
		} else {
			String.format("%d\u00B0 %s", temperature.value?.toInt(), temperature.unit)
		}
	}

	private fun formatMinMaxTemperature(temperature: Temperature): String {
		return String.format("%d\u00B0/%d\u00B0", temperature.minimum.value?.toInt(), temperature.maximum.value?.toInt())
	}

	private fun mapIconToId(weatherIcon: Int?, isDay: Boolean): Int {
		if (weatherIcon == null) {
			return if (isDay) R.drawable.day else R.drawable.night
		}

		return when (weatherIcon) {
			in (1..2) -> R.drawable.day
			in (3..5) -> R.drawable.partially_cloudy
			in (6..11) -> R.drawable.cloudy
			in listOf(12, 18, 39..40) -> R.drawable.rainy
			in (13..14) -> R.drawable.partially_rainy
			in (15..17) -> R.drawable.thunder
			in listOf(18..19, 22, 24..26, 29) -> R.drawable.snowy
			in listOf(21, 26) -> R.drawable.partially_snowy
			in (33..34) -> R.drawable.night
			else -> R.drawable.cloudy_night
		}
	}

	private fun formatWind(wind: Wind?): String {
		if (!hasWind(wind)) return ""

		return String.format("%d %s", wind!!.speed.value!!.toInt(), wind.speed.unit)
	}

	private fun hasWind(wind: Wind?): Boolean {
		return !(wind == null || wind.speed.value == null)
	}

	private fun formatHumidity(humidity: Int?): String {
		if (humidity == null) return ""

		return String.format("%d %%", humidity)
	}

	private fun formatUvIndex(index: Int?): String {
		if (index == null) return ""

		return index.toString()
	}
}