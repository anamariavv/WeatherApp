package source.network

import model.network.forecast.daily.ApiForecast
import model.network.forecast.current.ApiCurrentConditions
import model.network.forecast.hourly.ApiHourlyForecast
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ForecastService {
	//todo: data reduction, nullability

	@GET("/currentconditions/v1/{locationKey}")
	suspend fun getCurrentConditions(
		@Path("locationKey") locationKey: String,
		@Query("apikey") apiKey: String,
		@Query("details") details: Boolean,
	): ApiCurrentConditions

	@GET("/forecasts/v1/daily/1day/{locationKey}")
	suspend fun getDailyForecast(
			@Path("locationKey") locationKey: String,
			@Query("apikey") apiKey: String,
			@Query("details") details: Boolean,
			@Query("metric") metric: Boolean,
	): ApiForecast

	@GET("/forecasts/v1/hourly/12hour/{locationKey}")
	suspend fun getTwelveHourForecast(
			@Path("locationKey") locationKey: String,
			@Query("apikey") apiKey: String,
			@Query("details") details: Boolean,
			@Query("metric") metric: Boolean,
	): ApiHourlyForecast

	@GET("/forecasts/v1/daily/5day/{locationKey}")
	suspend fun getWeeklyForecast(
		@Path("locationKey") locationKey: String,
		@Query("apikey") apiKey: String,
		@Query("details") details: Boolean,
		@Query("metric") metric: Boolean,
	): ApiForecast
}