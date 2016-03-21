package vaeke.restcountries.v0.service

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import vaeke.restcountries.v0.domain.Country
import java.io.InputStreamReader
import java.text.Normalizer

/**
 * Created by gandrianakis on 18/3/2016.
 */
@Service
class CountryV0Service {

    internal val log = LoggerFactory.getLogger(CountryV0Service::class.java)

    val countries: List<Country> = loadCountries()

    fun getAll() = countries

    fun getByAlpha(alpha: String): Country? = countries.find { alphaCodesMatch(it, alpha) }

    private fun alphaCodesMatch(country: Country, alpha: String): Boolean {
        val appropriateField = if (alpha.length == 2) country.cca2 else country.cca3
        return appropriateField.equals(alpha, true)
    }

    fun getByCodeList(codes: List<String>) : List<Country> = codes.mapNotNull { getByAlpha(it) }

    fun getByCurrency(currency: String): List<Country> = countries.filter { it.currency.equals(currency, true) }

    fun getByName(name: String): List<Country> = countries.filter { isContainedInCountryName(it, name) }

    private fun isContainedInCountryName(country: Country, name: String) : Boolean{
        return arrayListOf(country.name, country.altSpellings).map { it.normalize() }.contains(name)
    }

    fun getByCallingcode(callingCode: String): List<Country> = countries.filter { it.callingCode.equals(callingCode) }

    fun getByCapital(capital: String) : List<Country> = countries.filter { it.capital.normalize().equals(capital.normalize(), true) }

    fun getByRegion(region: String): List<Country> = countries.filter { it.region.equals(region, true) }

    fun getBySubRegion(subRegion: String): List<Country> = countries.filter { it.subregion.equals(subRegion, true) }

    fun getByLanguage(language: String): List<Country> = countries.filter { countryHasLanguage(it, language)}

    private fun countryHasLanguage(country : Country, language: String) : Boolean = country.languages.firstOrNull{it.equals(language, true)} != null

    private fun String.normalize(): String {
        return Normalizer.normalize(this, Normalizer.Form.NFD).replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
    }

    private fun loadCountries(): List<Country> {
        log.debug("Loading JSON Database v0")
        val result: MutableList<Country> = arrayListOf()

        val stream = this.javaClass.classLoader.getResourceAsStream("countries.json")
        val gson = Gson()
        val reader = JsonReader(InputStreamReader(stream, "UTF-8"))
        reader.beginArray()
        while (reader.hasNext()) {
            result.add(gson.fromJson<Country>(reader, Country::class.java))
        }
        reader.endArray()
        reader.close()

        return result.toList()
    }
}