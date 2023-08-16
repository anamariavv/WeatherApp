package interactor

interface GetSelectedCityLocationKeyInteractor {

	suspend operator fun invoke(): String
}