package model.forecast

data class Headline(
	val category: String,
	val effectiveDate: String,
	val effectiveEpochDate: Int,
	val endDate: String,
	val endEpochDate: Int,
	val mobileLink: String,
	val severity: Int,
	val text: String
)