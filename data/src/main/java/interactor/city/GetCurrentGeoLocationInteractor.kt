package interactor.city

import android.location.Location

interface GetCurrentGeoLocationInteractor {
	suspend operator fun invoke(): Location
}