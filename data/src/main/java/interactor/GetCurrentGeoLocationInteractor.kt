package interactor

import android.location.Location

interface GetCurrentGeoLocationInteractor {
	suspend operator fun invoke(): Location
}