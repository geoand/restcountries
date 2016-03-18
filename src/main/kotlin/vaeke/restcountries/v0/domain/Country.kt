package vaeke.restcountries.v0.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

/**
 * Created by gandrianakis on 18/3/2016.
 */
@JsonPropertyOrder(alphabetic = true)
data class Country(
        var name: String,
        var currency: String,
        @JsonProperty("topLevelDomain") var tld: String,
        @JsonProperty("alpha2Code") var cca2: String,
        @JsonProperty("alpha3Code") var cca3: String,
        @JsonProperty("isoNumericCode") var ccn3: String,
        @JsonProperty("callingcode") var callingCode: String,
        var capital: String,
        var region: String,
        var subregion: String,
        var altSpellings: String,
        var relevance: String,
        var nationality: String,
        var latlng: List<Double>,
        var languages: List<String>,
        var translations: CountryTranslations,
        var population: Int,
        var area: Double,
        var gini: Double,
        var timezones: List<String>
)