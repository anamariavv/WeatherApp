package mapper.impl

import mapper.ForecastMapper
import model.forecast.DailyForecastInfo
import model.network.forecast.ApiDailyForecastInfo

class ForecastMapperImpl : ForecastMapper {

	override suspend fun toDailyForecastInfo(apiDailyForecastInfo: ApiDailyForecastInfo): DailyForecastInfo {
		//todo
	}
}