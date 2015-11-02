package vaeke.restcountries.v1.service;

import vaeke.restcountries.v1.domain.Country;

import java.util.List;

/**
 * Created by George Andrianakis on 31/10/2015.
 */
public interface CountryService {

    List<Country> getAll();

    Country getByAlpha(String alpha);

    List<Country> getByCodeList(List<String> codes);

    List<Country> getByCurrency(String currency);

    List<Country> getByName(String name, boolean fullText);

    List<Country> getByCallingcode(String callingcode);

    List<Country> getByCapital(String capital);

    List<Country> getByRegion(String region);

    List<Country> getBySubregion(String subregion);

    List<Country> getByLanguage(String language);
}
