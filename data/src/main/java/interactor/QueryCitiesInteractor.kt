package interactor

import model.network.city.ApiCity

interface QueryCitiesInteractor {
    suspend operator fun invoke(queryText: String): List<ApiCity>
}