package model.forecast

data class Measurement(
	val phrase: String?,
	val unit: String,
	val unitType: Int,
	val value: Double
)