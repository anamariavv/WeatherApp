package interactor.impl

import android.location.Location
import interactor.GetCurrentGeoLocationInteractor
import source.local.GeoLocationProvider

class GetCurrentGeoLocationInteractorImpl(private val geoLocationProvider: GeoLocationProvider) : GetCurrentGeoLocationInteractor {

	override suspend fun invoke(): Location {
		return geoLocationProvider.getCurrentGeoLocation()
	}
}