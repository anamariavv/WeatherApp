package mapper.impl

import mapper.CityMapper
import model.network.city.ApiCity
import model.network.city.ApiCountry
import model.city.City
import model.local.FavouriteCity

class CityMapperImpl : CityMapper {

    override fun toCity(apiCity: ApiCity, isFavourite: Boolean): City {
        return City(
            localizedName = apiCity.localizedName,
            locationKey = apiCity.key,
            countryId = apiCity.country.id,
            countryLocalizedName = apiCity.country.localizedName,
            rank = apiCity.rank,
            type = apiCity.type,
            isFavourite = isFavourite
        )
    }

    override fun toCity(favouriteCity: FavouriteCity): City {
        return City(
            localizedName = favouriteCity.localizedName,
            locationKey = favouriteCity.locationKey,
            countryId = favouriteCity.countryId,
            countryLocalizedName = favouriteCity.countryLocalizedName,
            rank = favouriteCity.rank,
            type = favouriteCity.type,
            isFavourite = true
        )
    }

    override fun toApiCity(city: City): ApiCity {
        return ApiCity(
                localizedName = city.localizedName,
                key = city.locationKey,
                country = ApiCountry(city.countryId, city.countryLocalizedName),
                rank = city.rank,
                type = city.type
        )
    }

    override fun toFavouriteCity(city: City): FavouriteCity {
        return FavouriteCity(
            locationKey = city.locationKey,
            localizedName = city.localizedName,
            rank = city.rank,
            type = city.type,
            countryId = city.countryId,
            countryLocalizedName = city.countryLocalizedName
        )
    }
}