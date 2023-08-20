package model.forecast

data class Measurement(
	val phrase: String?,
	val unit: String,
	val unitType: Int,
	val value: Double?
)  {
	constructor(unit: String, unitType: Int, value: Double?) : this(phrase = "", unit = unit, unitType = unitType, value = value)
}