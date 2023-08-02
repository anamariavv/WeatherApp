package source.local.impl

import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.tasks.await
import source.local.GeoLocationProvider

class GeoLocationProviderImpl(private val fusedLocationProviderClient: FusedLocationProviderClient) : GeoLocationProvider {
	private lateinit var cancellationTokenSource: CancellationTokenSource
	private lateinit var cancellationToken: CancellationToken

	override suspend fun getCurrentGeoLocation(): Location {
		//todo move cancellation source and token to better place - should be unique for each request
		cancellationTokenSource = CancellationTokenSource()
		cancellationToken = cancellationTokenSource.token

		return fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, cancellationToken).await()
	}
}