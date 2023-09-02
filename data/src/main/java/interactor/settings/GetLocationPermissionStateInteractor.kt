package interactor.settings

interface GetLocationPermissionStateInteractor {

	suspend operator fun invoke(): Boolean
}