/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package vaeke.restcountries.v1.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vaeke.restcountries.domain.ICountryRestSymbols;
import vaeke.restcountries.mvcsupport.RestControllerWithEmptyHandling;
import vaeke.restcountries.v1.domain.Country;
import vaeke.restcountries.v1.service.CountryService;

import java.util.Arrays;
import java.util.List;

@RestControllerWithEmptyHandling
@RequestMapping("${api.path}/v1")
public class CountryV1Controller {

	private static final Logger LOG = LoggerFactory.getLogger(CountryV1Controller.class);


    private final CountryService countryService;

    @Autowired
    public CountryV1Controller(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
	public List<Country> getAllCountries() {
		return countryService.getAll();
	}

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Country> getCountries() {
        return countryService.getAll();
    }
	
	@RequestMapping(value = "alpha/{alphacode}", method = RequestMethod.GET)
	public Country getByAlpha(@PathVariable("alphacode") String alpha) {
		LOG.info("Getting by alpha " + alpha);

        return countryService.getByAlpha(alpha);
	}

    @RequestMapping(value = "alpha", method = RequestMethod.GET)
    public List<Country> getByAlphaList(@RequestParam(value = "codes") String codes) {
        LOG.info("Getting by list " + codes);

        return countryService.getByCodeList(Arrays.asList(codes.split(ICountryRestSymbols.SEMICOLON)));
    }

    @RequestMapping(value = "currency/{currency}", method = RequestMethod.GET)
	public List<Country> getByCurrency(@PathVariable("currency") String currency) {
		LOG.info("Getting by currency " + currency);

        return countryService.getByCurrency(currency);
	}

    @RequestMapping(value = "name/{name}", method = RequestMethod.GET)
    public List<Country> getByName(@PathVariable("name") String name, @RequestParam(value = "fullText", defaultValue = "false") boolean fullText) {
        LOG.info("Getting by getByName " + name);

        return countryService.getByName(name, fullText);
    }

    @RequestMapping(value = "callingcode/{callingcode}", method = RequestMethod.GET)
    public List<Country> getByCallingCode(@PathVariable("callingcode") String callingCode) {
        LOG.info("Getting by getByCallingCode " + callingCode);

        return countryService.getByCallingcode(callingCode);
    }

    @RequestMapping(value = "capital/{capital}", method = RequestMethod.GET)
    public List<Country> getByCapital(@PathVariable("capital") String capital) {
        LOG.info("Getting by getByCapital " + capital);

        return countryService.getByCapital(capital);
    }

    @RequestMapping(value = "region/{region}", method = RequestMethod.GET)
    public List<Country> getByRegion(@PathVariable("region") String region) {
        LOG.info("Getting by getByRegion " + region);

        return countryService.getByRegion(region);
    }

    @RequestMapping(value = "subregion/{subregion}", method = RequestMethod.GET)
    public List<Country> getBySubregion(@PathVariable("subregion") String subregion) {
        LOG.info("Getting by getBySubregion " + subregion);

        return countryService.getBySubregion(subregion);
    }

    @RequestMapping(value = "lang/{lang}", method = RequestMethod.GET)
    public List<Country> getByLanguage(@PathVariable("lang") String lang) {
        LOG.info("Getting by getByLanguage " + lang);

        return countryService.getByLanguage(lang);
    }

}
