package source.network

import model.network.forecast.ApiDailyForecastInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ForecastService {

	@GET("/forecasts/v1/daily/1day/{locationKey}")
	suspend fun getDailyForecast(
			@Path("locationKey") locationKey: String,
			@Query("apikey") apiKey: String,
			@Query("details") details: Boolean = true,
			@Query("metric") metric: Boolean = true,
	): ApiDailyForecastInfo

	//todo: finish flow, return type will be different so make a new type - details not needed here, only basic info
	@GET("/forecasts/v1/daily/1day/{locationKey}")
	suspend fun get12HourlyForecast(
			@Path("locationKey") locationKey: String,
			@Query("apikey") apiKey: String,
			@Query("details") details: Boolean = true,
			@Query("metric") metric: Boolean = true,
	): ApiDailyForecastInfo

	//todo: finish flow
	@GET("/forecasts/v1/daily/5day/{locationKey}")
	suspend fun get5DayForecast(
		@Path("locationKey") locationKey: String,
		@Query("apikey") apiKey: String,
		@Query("details") details: Boolean = false,
		@Query("metric") metric: Boolean = true,
	): ApiDailyForecastInfo
}