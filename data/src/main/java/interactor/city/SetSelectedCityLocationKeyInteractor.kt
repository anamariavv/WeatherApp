package interactor.city

interface SetSelectedCityLocationKeyInteractor {

	suspend operator fun invoke(locationKey: String)
}