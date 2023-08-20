package mapper

import model.network.city.ApiCity
import model.city.City
import model.local.FavouriteCity

interface CityMapper {

    fun toCity(apiCity: ApiCity, isFavourite: Boolean): City

    fun toCity(favouriteCity: FavouriteCity): City

    fun toFavouriteCity(city: City): FavouriteCity

    fun toApiCity(city: City): ApiCity
}