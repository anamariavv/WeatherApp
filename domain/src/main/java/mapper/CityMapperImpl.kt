package mapper

import model.network.ApiCity
import model.network.ApiCountry
import model.City
import model.Country
import model.local.FavouriteCity
import model.local.FavouriteCityWithCountry
import model.local.FavouriteCountry

class CityMapperImpl : CityMapper {

    override fun toCity(apiCity: ApiCity): City {
        return City(
            localizedName = apiCity.localizedName,
            locationKey = apiCity.key,
            country = toCountry(apiCity.country),
            rank = apiCity.rank,
            type = apiCity.type,
            isFavourite = false
        )
    }

    override fun toCity(favouriteCity: FavouriteCityWithCountry): City {
        return City(
            localizedName = favouriteCity.city.localizedName,
            locationKey = favouriteCity.city.locationKey,
            country = toCountry(favouriteCity.country),
            rank = favouriteCity.city.rank,
            type = favouriteCity.city.type,
            isFavourite = true
        )
    }

    override fun toFavouriteCityWithCountry(city: City): FavouriteCityWithCountry {
        return FavouriteCityWithCountry(
            city = toFavouriteCity(city),
            country = toFavouriteCountry(city.country)
        )
    }

    override fun toApiCity(city: City): ApiCity {
        return ApiCity(
            localizedName = city.localizedName,
            key = city.locationKey,
            country = toApiCountry(city.country),
            rank = city.rank,
            type = city.type
        )
    }

    private fun toCountry(favouriteCountry: FavouriteCountry): Country {
        return Country(id = favouriteCountry.id, localizedName = favouriteCountry.localizedName)
    }

    private fun toFavouriteCity(city: City): FavouriteCity {
        return FavouriteCity(
            locationKey = city.locationKey,
            localizedName = city.localizedName,
            rank = city.rank,
            type = city.type,
            countryId = city.country.id
        )
    }

    private fun toFavouriteCountry(country: Country): FavouriteCountry {
        return FavouriteCountry(id = country.id, localizedName = country.localizedName)
    }

    private fun toCountry(apiCountry: ApiCountry): Country {
        return Country(id = apiCountry.id, localizedName = apiCountry.localizedName)
    }

    private fun toApiCountry(country: Country): ApiCountry {
        return ApiCountry(id = country.id, localizedName = country.localizedName)
    }
}