package interactor

interface GetLocationPermissionStateInteractor {

	suspend operator fun invoke(): Boolean
}