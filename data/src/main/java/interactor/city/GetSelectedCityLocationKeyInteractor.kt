package interactor.city

interface GetSelectedCityLocationKeyInteractor {

	suspend operator fun invoke(): String
}