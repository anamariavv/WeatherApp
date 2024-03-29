package source.network

import model.network.city.ApiCity
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationsService {
	@GET("/locations/v1/cities/autocomplete")
	suspend fun queryCities(
		@Query("apikey") apiKey: String,
		@Query("q") queryText: String
	): List<ApiCity>

	@GET("/locations/v1/cities/geoposition/search")
	suspend fun getLocationFromCoordinates(
		@Query("apikey") apiKey: String,
		@Query("q") coordinates: String
	): ApiCity
}