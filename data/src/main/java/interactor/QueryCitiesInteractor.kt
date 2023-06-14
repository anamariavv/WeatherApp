package interactor

interface QueryCitiesInteractor {
    suspend operator fun invoke(queryText: String): String
}