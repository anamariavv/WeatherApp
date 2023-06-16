package source.network

import model.ApiCity
import retrofit2.http.Field
import retrofit2.http.GET

interface LocationsService {
    @GET("/locations/v1/cities/autocomplete")
    suspend fun queryCities(
        @Field("apikey") apiKey: String,
        @Field("q") queryText: String
    ): List<ApiCity>
}