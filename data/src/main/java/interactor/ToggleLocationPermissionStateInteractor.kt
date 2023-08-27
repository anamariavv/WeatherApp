package interactor

interface ToggleLocationPermissionStateInteractor {

	suspend operator fun invoke(isGranted: Boolean)
}