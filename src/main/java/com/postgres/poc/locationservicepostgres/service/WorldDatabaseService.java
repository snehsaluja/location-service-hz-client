package com.postgres.poc.locationservicepostgres.service;

import com.postgres.poc.locationservicepostgres.dao.CityRepository;
import com.postgres.poc.locationservicepostgres.dao.CountryRepository;
import com.postgres.poc.locationservicepostgres.exception.NotFoundException;
import com.postgres.poc.locationservicepostgres.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorldDatabaseService {

    Logger logger = LoggerFactory.getLogger(WorldDatabaseService.class);

    /*@Autowired
    CityRepository cityRepository;

    *//*@Autowired
    CountryRepository countryRepository;*/

    public List<CountryDTO> getCountriesByPopulation(int population) {
        List<CountryDTO> countries = new ArrayList<>();
        logger.info("Calling findByPopulationGreaterThanEqualOrderByPopulationDesc()");
        /*for (Country entry :
                countryRepository.findByPopulationGreaterThanEqualOrderByPopulationDesc(population)) {

            countries.add(new CountryDTO(entry.getCode(), entry));
        }*/
        return countries;
    }

    public List<CityDTO> getCitiesByPopulation(int population) {
        List<CityDTO> cities = new ArrayList<>();
        logger.info("Calling findByPopulationGreaterThanEqualOrderByPopulationDesc()");
        /*for (City entry :
                cityRepository.findByPopulationGreaterThanEqualOrderByPopulationDesc(population)) {

            cities.add(new CityDTO(entry.getId(), entry));
        }*/
        return cities;
    }

    /*public List<City> getMostPopulatedCities(Integer limit) {
        logger.info("Calling findMostPopulatedCities()");
        List<City> cities = cityRepository.findMostPopulatedCities(limit == null ? 5 : limit);
        return cities;
    }*/

    //@CacheEvict(cacheNames = "City", key = "#id")
    public CityDTO updateCityPopulation(int id, int population) {
        logger.info("Calling findById()");
        /*City entry = cityRepository.findById(id);
        entry.setPopulation(population);

        cityRepository.save(entry);

        return new CityDTO(entry.getId(), entry);*/
        return null;

    }

    //@Cacheable(cacheNames = "City", key = "#id")
    public CityDTO getCityById(int id) {
        logger.info("Calling findById()");
        /*City entry = cityRepository.findById(id);
        return new CityDTO(entry.getId(), entry);*/

        return null;
    }

/*
    public CountryDTO updateCountryPopulation(String code, int population) throws NotFoundException {
        Country country = countryRepository.findById(code).orElseThrow(
                () -> new NotFoundException("Country Not Found"));
        country.setPopulation(population);
        countryRepository.save(country);
        return new CountryDTO(country.getCode(), country);
    }

    public CountryDTO getCountry(String code) throws NotFoundException {

        Country country = countryRepository.findById(code).orElseThrow(
                () -> new NotFoundException("Country Not Found"));
        return new CountryDTO(country.getCode(), country);
    }*/
}
