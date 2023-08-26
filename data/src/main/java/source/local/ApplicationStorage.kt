package source.local

interface ApplicationStorage {
	suspend fun saveString(key: String, value: String)

	suspend fun getString(key: String, default: String = ""): String

	suspend fun saveBoolean(key: String, value: Boolean)

	suspend fun getBoolean(key: String, default: Boolean = true): Boolean
}