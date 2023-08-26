package interactor

interface ToggleUnitsInteractor {

	suspend operator fun invoke(isMetric: Boolean)
}