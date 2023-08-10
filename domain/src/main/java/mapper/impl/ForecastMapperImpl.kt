package mapper.impl

import mapper.ForecastMapper
import model.forecast.*
import model.network.forecast.daily.ApiAirAndPollen
import model.network.forecast.daily.ApiDailyForecast
import model.network.forecast.daily.ApiDay
import model.network.forecast.daily.ApiForecast
import model.network.forecast.daily.ApiHeadline
import model.network.forecast.daily.ApiNight
import model.network.forecast.daily.ApiRealFeelTemperature
import model.network.forecast.daily.ApiTemperature
import model.network.forecast.daily.ApiWind

class ForecastMapperImpl : ForecastMapper {

	override suspend fun toDailyForecastInfo(apiForecast: ApiForecast): Forecast {
		val dailyForecastList = apiForecast.dailyForecasts.map { toDailyForecast(it) }
		val headline = toHeadline(apiForecast.headline)

		return Forecast(dailyForecastList, headline)
	}

	private fun toHeadline(apiHeadline: ApiHeadline): Headline {
		return Headline(category = apiHeadline.category,
		                effectiveDate = apiHeadline.effectiveDate,
		                effectiveEpochDate = apiHeadline.effectiveEpochDate,
		                endDate = apiHeadline.endDate,
		                endEpochDate = apiHeadline.endEpochDate,
		                mobileLink = apiHeadline.mobileLink,
		                severity = apiHeadline.severity,
		                text = apiHeadline.text)
	}

	private fun toDailyForecast(apiDailyForecast: ApiDailyForecast): DailyForecast {
		val airAndPollenList = apiDailyForecast.airAndPollen.map { toAirAndPollen(it) }

		return DailyForecast(airAndPollen = airAndPollenList,
		                     date = apiDailyForecast.date,
		                     day = toDay(apiDailyForecast.day),
		                     epochDate = apiDailyForecast.epochDate,
		                     hoursOfSun = apiDailyForecast.hoursOfSun,
		                     mobileLink = apiDailyForecast.mobileLink,
		                     moonRiseEpoch = apiDailyForecast.moon.epochRise,
		                     moonSetEpoch = apiDailyForecast.moon.epochSet,
		                     sunRiseEpoch = apiDailyForecast.sun.epochRise,
		                     sunSetEpoch = apiDailyForecast.sun.epochSet,
		                     night = toNight(apiDailyForecast.night),
		                     realFeelTemperature = toTemperature(apiDailyForecast.realFeelTemperature),
		                     realFeelTemperatureShade = toTemperature(apiDailyForecast.realFeelTemperatureShade),
		                     temperature = toTemperature(apiDailyForecast.temperature))
	}

	private fun toAirAndPollen(apiAirAndPollen: ApiAirAndPollen): AirAndPollen {
		return AirAndPollen(category = apiAirAndPollen.category,
		                    categoryValue = apiAirAndPollen.categoryValue,
		                    name = apiAirAndPollen.name,
		                    type = apiAirAndPollen.type,
		                    value = apiAirAndPollen.value)
	}

	//todo: combine day and night into sealed class
	private fun toDay(apiDay: ApiDay): Day {
		return Day(cloudCover = apiDay.cloudCover,
		           hasPrecipitation = apiDay.hasPrecipitation,
		           hoursOfIce = apiDay.hoursOfIce,
		           hoursOfPrecipitation = apiDay.hoursOfPrecipitation,
		           hoursOfRain = apiDay.hoursOfRain,
		           hoursOfSnow = apiDay.hoursOfSnow,
		           ice = Measurement(apiDay.ice.unit, apiDay.ice.unitType, apiDay.ice.value),
		           iceProbability = apiDay.iceProbability,
		           icon = apiDay.icon,
		           iconPhrase = apiDay.iconPhrase,
		           longPhrase = apiDay.longPhrase,
		           precipitationProbability = apiDay.precipitationProbability,
		           rain = Measurement(apiDay.rain.unit, apiDay.rain.unitType, apiDay.rain.value),
		           rainProbability = apiDay.rainProbability,
		           shortPhrase = apiDay.shortPhrase,
		           snow = Measurement(apiDay.snow.unit, apiDay.snow.unitType, apiDay.snow.value),
		           snowProbability = apiDay.snowProbability,
		           thunderstormProbability = apiDay.thunderstormProbability,
		           totalLiquid = Measurement(apiDay.totalLiquid.unit, apiDay.totalLiquid.unitType, apiDay.totalLiquid.value),
		           wind = toWind(apiDay.wind),
		           windGust = toWind(apiDay.windGust))
	}

	private fun toNight(apiNight: ApiNight): Night {
		return Night(cloudCover = apiNight.cloudCover,
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
		             windGust = toWind(apiNight.windGust))
	}

	private fun toWind(apiWind: ApiWind): Wind {
		val direction = Direction(apiWind.direction.degrees, apiWind.direction.english, apiWind.direction.localized)
		val measurement = Measurement(apiWind.speed.unit, apiWind.speed.unitType, apiWind.speed.value)
		return Wind(direction, measurement)
	}

	private fun toTemperature(apiRealFeelTemperature: ApiRealFeelTemperature): Temperature {
		return Temperature(maximum = Measurement(apiRealFeelTemperature.maximum.phrase,
		                                         apiRealFeelTemperature.maximum.unit,
		                                         apiRealFeelTemperature.maximum.unitType,
		                                         apiRealFeelTemperature.maximum.value),
		                   minimum = Measurement(apiRealFeelTemperature.minimum.phrase,
		                                         apiRealFeelTemperature.minimum.unit,
		                                         apiRealFeelTemperature.minimum.unitType,
		                                         apiRealFeelTemperature.minimum.value))
	}

	private fun toTemperature(apiTemperature: ApiTemperature): Temperature {
		return Temperature(maximum = Measurement(apiTemperature.maximum.unit,
		                                         apiTemperature.maximum.unitType,
		                                         apiTemperature.maximum.value),
		                   minimum = Measurement(apiTemperature.minimum.unit,
		                                         apiTemperature.minimum.unitType,
		                                         apiTemperature.minimum.value))
	}

}