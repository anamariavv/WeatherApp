package model.network.forecast.daily


import com.google.gson.annotations.SerializedName

data class ApiNight(
	@SerializedName("CloudCover")
	val cloudCover: Int,
	@SerializedName("HasPrecipitation")
	val hasPrecipitation: Boolean,
	@SerializedName("HoursOfIce")
	val hoursOfIce: Int,
	@SerializedName("HoursOfPrecipitation")
	val hoursOfPrecipitation: Int,
	@SerializedName("HoursOfRain")
	val hoursOfRain: Int,
	@SerializedName("HoursOfSnow")
	val hoursOfSnow: Int,
	@SerializedName("Ice")
	val ice: ApiMeasurement,
	@SerializedName("IceProbability")
	val iceProbability: Int,
	@SerializedName("Icon")
	val icon: Int,
	@SerializedName("IconPhrase")
	val iconPhrase: String,
	@SerializedName("LongPhrase")
	val longPhrase: String,
	@SerializedName("PrecipitationProbability")
	val precipitationProbability: Int,
	@SerializedName("Rain")
	val rain: ApiMeasurement,
	@SerializedName("RainProbability")
	val rainProbability: Int,
	@SerializedName("ShortPhrase")
	val shortPhrase: String,
	@SerializedName("Snow")
	val snow: ApiMeasurement,
	@SerializedName("SnowProbability")
	val snowProbability: Int,
	@SerializedName("ThunderstormProbability")
	val thunderstormProbability: Int,
	@SerializedName("TotalLiquid")
	val totalLiquid: ApiMeasurement,
	@SerializedName("Wind")
	val wind: ApiWind,
	@SerializedName("WindGust")
	val windGust: ApiWind
)