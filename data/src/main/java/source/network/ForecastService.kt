package source.network

import model.network.forecast.daily.ApiForecast
import model.network.forecast.current.ApiCurrentConditions
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ForecastService {
	//todo: data reduction, nullability

	@GET("/currentconditions/v1/{locationKey}")
	//todo: finish flow
	suspend fun getCurrentConditions(
		@Path("locationKey") locationKey: String,
		@Query("apikey") apiKey: String,
		@Query("details") details: Boolean = true,
	): ApiCurrentConditions

	@GET("/forecasts/v1/daily/1day/{locationKey}")
	suspend fun getDailyForecast(
			@Path("locationKey") locationKey: String,
			@Query("apikey") apiKey: String,
			@Query("details") details: Boolean = true,
			@Query("metric") metric: Boolean = true,
	): ApiForecast

	//todo: finish flow, return type will be different so make a new type - details not needed here, only basic info
	@GET("/forecasts/v1/hourly/12hour/{locationKey}")
	suspend fun get12HourlyForecast(
			@Path("locationKey") locationKey: String,
			@Query("apikey") apiKey: String,
			@Query("details") details: Boolean = true,
			@Query("metric") metric: Boolean = true,
	): ApiForecast

	//todo: finish flow
	@GET("/forecasts/v1/daily/5day/{locationKey}")
	suspend fun get5DayForecast(
		@Path("locationKey") locationKey: String,
		@Query("apikey") apiKey: String,
		@Query("details") details: Boolean = false,
		@Query("metric") metric: Boolean = true,
	): ApiForecast
}