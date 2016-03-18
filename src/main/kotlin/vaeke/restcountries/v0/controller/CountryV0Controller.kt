package vaeke.restcountries.v0.controller

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import vaeke.restcountries.domain.ICountryRestSymbols
import vaeke.restcountries.mvcsupport.RestControllerWithEmptyHandling
import vaeke.restcountries.v0.domain.Country
import vaeke.restcountries.v0.service.CountryV0Service

/**
 * Created by gandrianakis on 18/3/2016.
 */
@RestControllerWithEmptyHandling
@RequestMapping("\${api.path}/v0")
class CountryV0Controller @Autowired constructor(private val countryService: CountryV0Service) {

    internal val log = LoggerFactory.getLogger(CountryV0Service::class.java)

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun default() = countryService.countries

    @RequestMapping(value = "all", method = arrayOf(RequestMethod.GET))
    fun all() = countryService.countries

    @RequestMapping(value = "alpha/{alphacode}", method = arrayOf(RequestMethod.GET))
    fun getByAlpha(@PathVariable("alphacode") alpha: String): Country? {
        log.info("Getting by alpha " + alpha)

        return countryService.getByAlpha(alpha)
    }

    @RequestMapping(value = "alpha2/{alphacode}", method = arrayOf(RequestMethod.GET))
    @Deprecated("")
    fun getByAlpha2(@PathVariable("alphacode") alpha: String): Country? {
        log.info("Getting by alpha " + alpha)

        return countryService.getByAlpha(alpha)
    }

    @RequestMapping(value = "alpha3/{alphacode}", method = arrayOf(RequestMethod.GET))
    @Deprecated("")
    fun getByAlpha3(@PathVariable("alphacode") alpha: String): Country? {
        log.info("Getting by alpha " + alpha)

        return countryService.getByAlpha(alpha)
    }

    @RequestMapping(value = "alpha", method = arrayOf(RequestMethod.GET))
    fun getByAlphaList(@RequestParam(value = "codes") codesStr: String): List<Country> {
        log.info("Getting by list " + codesStr)

        return countryService.getByCodeList(codesStr.split(ICountryRestSymbols.SEMICOLON).filter { it.isEmpty() }.map { it.trim() })
    }

    @RequestMapping(value = "currency/{currency}", method = arrayOf(RequestMethod.GET))
    fun getByCurrency(@PathVariable("currency") currency: String): List<Country> {
        log.info("Getting by currency " + currency)

        return countryService.getByCurrency(currency)
    }

    @RequestMapping(value = "name/{name}", method = arrayOf(RequestMethod.GET))
    fun getByName(@PathVariable("name") name: String): List<Country> {
        log.info("Getting by getByName " + name)

        return countryService.getByName(name)
    }

    @RequestMapping(value = "callingcode/{callingcode}", method = arrayOf(RequestMethod.GET))
    fun getByCallingCode(@PathVariable("callingcode") callingCode: String): List<Country> {
        log.info("Getting by getByCallingCode " + callingCode)

        return countryService.getByCallingcode(callingCode)
    }

    @RequestMapping(value = "capital/{capital}", method = arrayOf(RequestMethod.GET))
    fun getByCapital(@PathVariable("capital") capital: String): List<Country> {
        log.info("Getting by getByCapital " + capital)

        return countryService.getByCapital(capital)
    }

    @RequestMapping(value = "region/{region}", method = arrayOf(RequestMethod.GET))
    fun getByRegion(@PathVariable("region") region: String): List<Country> {
        log.info("Getting by getByRegion " + region)

        return countryService.getByRegion(region)
    }

    @RequestMapping(value = "subregion/{subregion}", method = arrayOf(RequestMethod.GET))
    fun getBySubregion(@PathVariable("subregion") subregion: String): List<Country> {
        log.info("Getting by getBySubregion " + subregion)

        return countryService.getBySubRegion(subregion)
    }

    @RequestMapping(value = "lang/{lang}", method = arrayOf(RequestMethod.GET))
    fun getByLanguage(@PathVariable("lang") lang: String): List<Country> {
        log.info("Getting by getByLanguage " + lang)

        return countryService.getByLanguage(lang)
    }
}