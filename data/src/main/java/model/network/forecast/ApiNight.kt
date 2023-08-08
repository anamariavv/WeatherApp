package model.network.forecast


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
	val ice: ApiIce,
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
	val rain: ApiRain,
	@SerializedName("RainProbability")
	val rainProbability: Int,
	@SerializedName("ShortPhrase")
	val shortPhrase: String,
	@SerializedName("Snow")
	val snow: ApiSnow,
	@SerializedName("SnowProbability")
	val snowProbability: Int,
	@SerializedName("ThunderstormProbability")
	val thunderstormProbability: Int,
	@SerializedName("TotalLiquid")
	val totalLiquid: ApiTotalLiquid,
	@SerializedName("Wind")
	val wind: ApiWind,
	@SerializedName("WindGust")
	val windGust: ApiWind
)