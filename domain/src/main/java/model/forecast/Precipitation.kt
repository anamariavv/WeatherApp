package model.forecast

sealed class Precipitation(val unit: String, val measurement: Measurement) {

	class Ice(unit: String, measurement: Measurement) : Precipitation(unit = unit, measurement = measurement)

	class Rain(unit: String, measurement: Measurement) : Precipitation(unit = unit, measurement = measurement)

	class Snow(unit: String, measurement: Measurement) : Precipitation(unit = unit, measurement = measurement)
}
