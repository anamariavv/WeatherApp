package mapper

import model.network.ApiCity
import model.City
import model.local.FavouriteCity

interface CityMapper {

    fun toCity(apiCity: ApiCity): City

    fun toCity(favouriteCity: FavouriteCity): City

    fun toFavouriteCity(city: City): FavouriteCity

    fun toApiCity(city: City): ApiCity
}