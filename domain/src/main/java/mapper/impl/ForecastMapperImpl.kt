package mapper.impl

import mapper.ForecastMapper
import model.forecast.*
import model.forecast.CurrentConditions
import model.network.forecast.current.ApiCurrentConditionsItem
import model.network.forecast.current.ApiWindCurrent
import model.network.forecast.daily.ApiAirAndPollen
import model.network.forecast.daily.ApiDailyForecast
import model.network.forecast.daily.ApiDay
import model.network.forecast.daily.ApiForecast
import model.network.forecast.daily.ApiHeadline
import model.network.forecast.daily.ApiNight
import model.network.forecast.daily.ApiTemperature
import model.network.forecast.daily.ApiWind
import model.network.forecast.hourly.ApiHourlyForecast
import model.network.forecast.hourly.ApiHourlyForecastItem

class ForecastMapperImpl : ForecastMapper {

	override fun toCurrentConditionsMetric(apiItem: ApiCurrentConditionsItem): CurrentConditions {
		return CurrentConditions(
			epochTime = apiItem.epochTime,
			hasPrecipitation = apiItem.hasPrecipitation,
			isDayTime = apiItem.isDayTime,
			localObservationDateTime = apiItem.localObservationDateTime,
			mobileLink = apiItem.mobileLink,
			obstructionsToVisibility = apiItem.obstructionsToVisibility,
			precipitationType = apiItem.precipitationType,
			pressure = Measurement(apiItem.pressure.metric.unit, apiItem.pressure.metric.unitType, apiItem.pressure.metric.value),
			realFeelTemperature = Measurement(
				apiItem.realFeelTemperature.metric.phrase,
				apiItem.realFeelTemperature.metric.unit,
				apiItem.realFeelTemperature.metric.unitType,
				apiItem.realFeelTemperature.metric.value
			),
			realFeelTemperatureShade = Measurement(
				apiItem.realFeelTemperatureShade.metric.phrase,
				apiItem.realFeelTemperatureShade.metric.unit,
				apiItem.realFeelTemperatureShade.metric.unitType,
				apiItem.realFeelTemperatureShade.metric.value
			),
			relativeHumidity = apiItem.relativeHumidity,
			temperature = Measurement(apiItem.temperature.metric.unit, apiItem.temperature.metric.unitType, apiItem.temperature.metric.value),
			uVIndex = apiItem.uVIndex,
			uVIndexText = apiItem.uVIndexText,
			visibility = Measurement(apiItem.visibility.metric.unit, apiItem.visibility.metric.unitType, apiItem.visibility.metric.value),
			weatherText = apiItem.weatherText,
			weatherIcon = apiItem.weatherIcon,
			wind = toWindCurrent(apiItem.wind, isMetric = true),
			windGust = toWindCurrent(apiItem.windGust, isMetric = true)
		)
	}

	override fun toCurrentConditionsImperial(apiItem: ApiCurrentConditionsItem): CurrentConditions {
		return CurrentConditions(
			epochTime = apiItem.epochTime,
			hasPrecipitation = apiItem.hasPrecipitation,
			isDayTime = apiItem.isDayTime,
			localObservationDateTime = apiItem.localObservationDateTime,
			mobileLink = apiItem.mobileLink,
			obstructionsToVisibility = apiItem.obstructionsToVisibility,
			precipitationType = apiItem.precipitationType,
			pressure = Measurement(apiItem.pressure.imperial.unit, apiItem.pressure.imperial.unitType, apiItem.pressure.imperial.value),
			realFeelTemperature = Measurement(
				apiItem.realFeelTemperature.imperial.phrase,
				apiItem.realFeelTemperature.imperial.unit,
				apiItem.realFeelTemperature.imperial.unitType,
				apiItem.realFeelTemperature.imperial.value
			),
			realFeelTemperatureShade = Measurement(
				apiItem.realFeelTemperatureShade.imperial.phrase,
				apiItem.realFeelTemperatureShade.imperial.unit,
				apiItem.realFeelTemperatureShade.imperial.unitType,
				apiItem.realFeelTemperatureShade.imperial.value
			),
			relativeHumidity = apiItem.relativeHumidity,
			temperature = Measurement(apiItem.temperature.imperial.unit, apiItem.temperature.imperial.unitType, apiItem.temperature.imperial.value),
			uVIndex = apiItem.uVIndex,
			uVIndexText = apiItem.uVIndexText,
			visibility = Measurement(apiItem.visibility.imperial.unit, apiItem.visibility.imperial.unitType, apiItem.visibility.imperial.value),
			weatherText = apiItem.weatherText,
			weatherIcon = apiItem.weatherIcon,
			wind = toWindCurrent(apiItem.wind, isMetric = false),
			windGust = toWindCurrent(apiItem.windGust, isMetric = false)
		)
	}

	override fun toDailyForecastInfo(apiForecast: ApiForecast): Forecast {
		val dailyForecastList = apiForecast.dailyForecasts.map { toDailyForecast(it) }
		val headline = toHeadline(apiForecast.headline)

		return Forecast(dailyForecastList, headline)
	}

	private fun toHeadline(apiHeadline: ApiHeadline): Headline {
		return Headline(
			category = apiHeadline.category,
			effectiveDate = apiHeadline.effectiveDate,
			effectiveEpochDate = apiHeadline.effectiveEpochDate,
			endDate = apiHeadline.endDate,
			endEpochDate = apiHeadline.endEpochDate,
			mobileLink = apiHeadline.mobileLink,
			severity = apiHeadline.severity,
			text = apiHeadline.text
		)
	}

	private fun toDailyForecast(apiDailyForecast: ApiDailyForecast): DailyForecast {

		return DailyForecast(
			date = apiDailyForecast.date,
			day = toDay(apiDailyForecast.day),
			icon = apiDailyForecast.day.icon,
			epochDate = apiDailyForecast.epochDate,
			mobileLink = apiDailyForecast.mobileLink,
			temperature = toTemperature(apiDailyForecast.temperature)
		)
	}

	override fun toHourlyForecast(apiForecast: ApiHourlyForecast): HourlyForecast {
		val items = apiForecast.map { toHourlyForecastItem(it) }
		return HourlyForecast(items)
	}

	private fun toHourlyForecastItem(apiItem: ApiHourlyForecastItem): HourlyForecastItem {
		return HourlyForecastItem(
			dateTime = apiItem.dateTime,
			epochDateTime = apiItem.epochDateTime,
			hasPrecipitation = apiItem.hasPrecipitation,
			iconPhrase = apiItem.iconPhrase,
			isDaylight = apiItem.isDaylight,
			mobileLink = apiItem.mobileLink,
			precipitationIntensity = apiItem.precipitationIntensity,
			precipitationProbability = apiItem.precipitationProbability,
			precipitationType = apiItem.precipitationType,
			temperature = Measurement(apiItem.temperature.unit, apiItem.temperature.unitType, apiItem.temperature.value),
			weatherIcon = apiItem.weatherIcon
		)
	}

	private fun toAirAndPollen(apiAirAndPollen: ApiAirAndPollen): AirAndPollen {
		return AirAndPollen(
			category = apiAirAndPollen.category,
			categoryValue = apiAirAndPollen.categoryValue,
			name = apiAirAndPollen.name,
			type = apiAirAndPollen.type,
			value = apiAirAndPollen.value
		)
	}

	private fun toDay(apiDay: ApiDay): Day {
		return Day(
			icon = apiDay.icon,
			iconPhrase = apiDay.iconPhrase,
		)
	}

	private fun toNight(apiNight: ApiNight): Night {
		return Night(
			cloudCover = apiNight.cloudCover,
			hasPrecipitation = apiNight.hasPrecipitation,
			hoursOfIce = apiNight.hoursOfIce,
			hoursOfPrecipitation = apiNight.hoursOfPrecipitation,
			hoursOfRain = apiNight.hoursOfRain,
			hoursOfSnow = apiNight.hoursOfSnow,
			ice = Measurement(apiNight.ice.unit, apiNight.ice.unitType, apiNight.ice.value),
			iceProbability = apiNight.iceProbability,
			icon = apiNight.icon,
			iconPhrase = apiNight.iconPhrase,
			longPhrase = apiNight.longPhrase,
			precipitationProbability = apiNight.precipitationProbability,
			rain = Measurement(apiNight.rain.unit, apiNight.rain.unitType, apiNight.rain.value),
			rainProbability = apiNight.rainProbability,
			shortPhrase = apiNight.shortPhrase,
			snow = Measurement(apiNight.snow.unit, apiNight.snow.unitType, apiNight.snow.value),
			snowProbability = apiNight.snowProbability,
			thunderstormProbability = apiNight.thunderstormProbability,
			totalLiquid = Measurement(apiNight.totalLiquid.unit, apiNight.totalLiquid.unitType, apiNight.totalLiquid.value),
			wind = toWind(apiNight.wind),
			windGust = toWind(apiNight.windGust)
		)
	}

	private fun toWind(apiWind: ApiWind): Wind {
		val direction = Direction(apiWind.direction?.degrees, apiWind.direction?.english, apiWind.direction?.localized)
		val measurement = Measurement(apiWind.speed.unit, apiWind.speed.unitType, apiWind.speed.value)
		return Wind(direction, measurement)
	}

	private fun toWindCurrent(apiWindCurrent: ApiWindCurrent, isMetric: Boolean): Wind {
		val direction = Direction(apiWindCurrent.direction?.degrees, apiWindCurrent.direction?.english, apiWindCurrent.direction?.localized)

		val measurement = if (isMetric) {
			Measurement(apiWindCurrent.speed.metric.unit, apiWindCurrent.speed.metric.unitType, apiWindCurrent.speed.metric.value)
		} else {
			Measurement(apiWindCurrent.speed.imperial.unit, apiWindCurrent.speed.imperial.unitType, apiWindCurrent.speed.imperial.value)
		}

		return Wind(direction, measurement)
	}

	private fun toTemperature(apiTemperature: ApiTemperature): Temperature {
		return Temperature(
			maximum = Measurement(
				apiTemperature.maximum.unit,
				apiTemperature.maximum.unitType,
				apiTemperature.maximum.value
			),
			minimum = Measurement(
				apiTemperature.minimum.unit,
				apiTemperature.minimum.unitType,
				apiTemperature.minimum.value
			)
		)
	}

}