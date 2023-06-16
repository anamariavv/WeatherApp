package mapper

import model.ApiCity
import model.City

interface CityMapper {

    fun toCity(apiCity: ApiCity): City

    fun toApiCity(city: City): ApiCity
}