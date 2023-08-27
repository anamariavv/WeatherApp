package interactor.impl

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import interactor.GetLocationPermissionStateInteractor
import source.local.ApplicationStorage

class GetLocationPermissionStateInteractorImpl(private val context: Context, private val applicationStorage: ApplicationStorage) : GetLocationPermissionStateInteractor {

	companion object {
		const val key = "LOCATION_PERMISSION_GRANTED"
	}

	override suspend fun invoke(): Boolean {
		return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
		//return applicationStorage.getBoolean(key, false)
	}


}