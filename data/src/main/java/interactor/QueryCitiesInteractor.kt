package interactor

import model.ApiCity

interface QueryCitiesInteractor {
    suspend operator fun invoke(queryText: String): List<ApiCity>
}