package mapper

import model.forecast.DailyForecastInfo
import model.network.forecast.ApiDailyForecastInfo

interface ForecastMapper {

	suspend fun toDailyForecastInfo(apiDailyForecastInfo: ApiDailyForecastInfo): DailyForecastInfo
}