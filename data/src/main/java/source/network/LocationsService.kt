package source.network

import model.ApiCity
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationsService {
    @GET("/locations/v1/cities/autocomplete")
    suspend fun queryCities(
        @Query("apikey") apiKey: String,
        @Query("q") queryText: String
    ): List<ApiCity>
}