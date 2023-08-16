package source.local

interface ApplicationStorage {
	suspend fun saveString(key: String, value: String)

	suspend fun getString(key: String, default: String = ""): String
}