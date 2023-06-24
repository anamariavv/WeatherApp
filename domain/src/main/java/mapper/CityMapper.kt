package mapper

import model.network.ApiCity
import model.City
import model.local.FavouriteCity
import model.local.FavouriteCityWithCountry

interface CityMapper {

    fun toCity(apiCity: ApiCity): City

    fun toCity(favouriteCity: FavouriteCityWithCountry): City

    fun toFavouriteCityWithCountry(city: City): FavouriteCityWithCountry

    fun toApiCity(city: City): ApiCity
}