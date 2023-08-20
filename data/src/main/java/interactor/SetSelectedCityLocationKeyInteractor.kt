package interactor

interface SetSelectedCityLocationKeyInteractor {

	suspend operator fun invoke(locationKey: String)
}