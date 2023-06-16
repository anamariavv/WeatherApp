package mapper

import model.ApiCity
import model.ApiCountry
import model.City
import model.Country

class CityMapperImpl : CityMapper {

    override fun toCity(apiCity: ApiCity): City {
        return City(localizedName = apiCity.localizedName, locationKey = apiCity.key, country = toCountry(apiCity.country) , rank = apiCity.rank, type = apiCity.type )
    }

    override fun toApiCity(city: City): ApiCity {
        return ApiCity(localizedName = city.localizedName, key = city.locationKey, country = toApiCountry(city.country), rank = city.rank, type = city.type)
    }

    private fun toCountry(apiCountry: ApiCountry): Country {
        return Country(id = apiCountry.id, localizedName = apiCountry.localizedName)
    }

    private fun toApiCountry(country: Country): ApiCountry {
        return ApiCountry(id = country.id, localizedName = country.localizedName)
    }
}