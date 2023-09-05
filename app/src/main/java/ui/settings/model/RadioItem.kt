package ui.settings.model

sealed class RadioItem(val text: String, val isMetric: Boolean) {

	object Metric : RadioItem(text = "Metric units", isMetric = true)

	object Imperial : RadioItem(text = "Imperial units", isMetric = false)
}
