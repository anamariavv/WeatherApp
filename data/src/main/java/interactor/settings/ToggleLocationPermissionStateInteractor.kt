package interactor.settings

interface ToggleLocationPermissionStateInteractor {

	suspend operator fun invoke(isGranted: Boolean)
}