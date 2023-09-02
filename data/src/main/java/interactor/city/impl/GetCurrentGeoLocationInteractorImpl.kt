package interactor.city.impl

import android.location.Location
import interactor.city.GetCurrentGeoLocationInteractor
import source.local.GeoLocationProvider

class GetCurrentGeoLocationInteractorImpl(private val geoLocationProvider: GeoLocationProvider) : GetCurrentGeoLocationInteractor {

	override suspend fun invoke(): Location {
		return geoLocationProvider.getCurrentGeoLocation()
	}
}