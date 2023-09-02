package interactor.settings

interface ToggleUnitsInteractor {

	suspend operator fun invoke(isMetric: Boolean)
}