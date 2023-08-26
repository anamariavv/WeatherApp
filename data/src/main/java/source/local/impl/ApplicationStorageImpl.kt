package source.local.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import source.local.ApplicationStorage

class ApplicationStorageImpl(private val dataStorePreferences: DataStore<Preferences>) : ApplicationStorage {

	override suspend fun saveString(key: String, value: String) {
		dataStorePreferences.edit { preferences ->
			preferences[stringPreferencesKey(key)] = value
		}
	}

	override suspend fun getString(key: String, default: String): String {
		return dataStorePreferences.data
			.catch {
				throw it
			}.map { preferences ->
				preferences[stringPreferencesKey(key)] ?: default
			}.first()
	}

	override suspend fun saveBoolean(key: String, value: Boolean) {
		dataStorePreferences.edit { preferences ->
			preferences[booleanPreferencesKey(key)] = value
		}
	}

	override suspend fun getBoolean(key: String, default: Boolean): Boolean {
		return dataStorePreferences.data
			.catch {
				throw it
			}.map { preferences ->
				preferences[booleanPreferencesKey(key)] ?: default
			}.first()
	}
}