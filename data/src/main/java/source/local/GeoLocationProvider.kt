package source.local

import android.location.Location

interface GeoLocationProvider {
	suspend fun getCurrentGeoLocation(): Location
}