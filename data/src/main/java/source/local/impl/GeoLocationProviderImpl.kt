package source.local.impl

import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.tasks.await
import source.local.GeoLocationProvider

class GeoLocationProviderImpl(private val fusedLocationProviderClient: FusedLocationProviderClient) : GeoLocationProvider {
	override suspend fun getCurrentGeoLocation(): Location {
		return fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, CancellationTokenSource().token).await()
	}
}