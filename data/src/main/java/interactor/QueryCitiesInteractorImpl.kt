package interactor

import model.ApiCity
import model.ApiCountry
import source.network.LocationsService

class QueryCitiesInteractorImpl(
    private val locationsService: LocationsService
) : QueryCitiesInteractor {

    override suspend fun invoke(queryText: String): List<ApiCity> {
        //return locationsService.queryCities(apiKey = "GNGOwBdyEEgIIXzFdlGlqpOg46kVNz6I", queryText=queryText)
        return listOf(ApiCity(localizedName="Versailles", key="133875", country= ApiCountry(id="FR", localizedName="France"), rank=41, type="City"),
                ApiCity(localizedName="Versmold", key="170503", country=ApiCountry(id="DE", localizedName="Germany"), rank=63, type="City"),
         ApiCity(localizedName="Versalles", key="1228053", country=ApiCountry(id="AR", localizedName="Argentina"), rank=65, type="City"),
         ApiCity(localizedName="Versalles", key="32812", country=ApiCountry(id="BO", localizedName="Bolivia"), rank=65, type="City"),
         ApiCity(localizedName="Versoix", key="1754", country=ApiCountry(id="CH", localizedName="Switzerland"), rank=65, type="City"),
         ApiCity(localizedName="Versbach", key="987960", country=ApiCountry(id="DE", localizedName="Germany"), rank=65, type="City"),
         ApiCity(localizedName="Versko", key="48547", country=ApiCountry(id="BG", localizedName="Bulgaria"), rank=75, type="City"),
         ApiCity(localizedName="Vers-lEglise", key="316297", country=ApiCountry(id="CH", localizedName="Switzerland"), rank=75, type="City"),
         ApiCity(localizedName="Versis", key="231428", country=ApiCountry(id="LV", localizedName="Latvia"), rank=75, type="City"),
         ApiCity(localizedName="Versailles", key="333317", country=ApiCountry(id="US", localizedName="United States"), rank=75, type="City"))
    }
}