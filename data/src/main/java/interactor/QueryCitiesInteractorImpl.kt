package interactor

import android.util.Log
import model.ApiCity
import source.network.LocationsService

class QueryCitiesInteractorImpl(
    private val locationsService: LocationsService
) : QueryCitiesInteractor {

    override suspend fun invoke(queryText: String): List<ApiCity> {
        return locationsService.queryCities(apiKey = "GNGOwBdyEEgIIXzFdlGlqpOg46kVNz6I", queryText=queryText)
    }
}