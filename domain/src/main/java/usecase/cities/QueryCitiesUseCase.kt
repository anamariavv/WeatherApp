package usecase.cities

interface QueryCitiesUseCase {
    suspend operator fun invoke(queryText: String): String
}