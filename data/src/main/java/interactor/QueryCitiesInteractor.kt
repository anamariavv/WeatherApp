package interactor

import model.network.ApiCity

interface QueryCitiesInteractor {
    suspend operator fun invoke(queryText: String): List<ApiCity>
}