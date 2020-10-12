package com.postgres.poc.locationservicepostgres.service;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.postgres.poc.locationservicepostgres.exception.NotFoundException;
import com.postgres.poc.locationservicepostgres.model.Country;
import com.postgres.poc.locationservicepostgres.model.CountryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class CountryService {

    private static final int MAX_LIMIT = 100000;

    @Autowired
    HazelcastInstance hazelcastInstance;

    public CountryDTO updateCountryPopulation(String code, int population) throws NotFoundException {
        IMap<String, Country> iMap = hazelcastInstance.getMap("Country");
        Country country = iMap.get(code);
        country.setPopulation(population);
        iMap.put(code, country);
        return new CountryDTO(country.getCode(), country);
    }

    public CountryDTO getCountry(String code) throws NotFoundException {
        IMap<String, Country> iMap = hazelcastInstance.getMap("Country");
        Country country = iMap.get(code);
        //log.info("Values in map =>>>> ");
        /*for (Map.Entry<String, Country> entry : iMap.entrySet()) {
            System.out.println("{\"code\": \""+entry.getKey()+"\"},");
        }*/

        return new CountryDTO(country.getCode(), country);
    }

    public CountryDTO postCountry(CountryDTO countryDTO) {
        IMap<String, Country> iMap = hazelcastInstance.getMap("Country");
        Country country = Country.builder()
                .code(countryDTO.getCode())
                .name(countryDTO.getName())
                .continent(countryDTO.getContinent())
                .region(countryDTO.getRegion())
                .population(countryDTO.getPopulation())
                .build();
        iMap.put(country.getCode(), country);
        return countryDTO;
    }

    public void postRandomCountries() {
        IMap<String, Country> iMap = hazelcastInstance.getMap("Country");
        Map<String, Country> countryMap = new HashMap<>();
        for (int i = 0; i < MAX_LIMIT; i++) {
            Country country = Country.builder()
                    .code(String.valueOf(i))
                    .name("Country " + i)
                    .continent("Asia")
                    .region("Southern and Central Asia")
                    .surfaceArea(BigDecimal.valueOf(99.99))
                    .indepYear((short) 2010)
                    .population(100 + i)
                    .lifeExpectancy(BigDecimal.valueOf(45.54))
                    .gnp(BigDecimal.valueOf(1000000))
                    .gnpOld(BigDecimal.valueOf(10000))
                    .localName("XYZ " + i)
                    .governmentform("GovXYZ " + i)
                    .headOfState("Head-XYZ " + i)
                    .capital(9999)
                    .code2("C" + i)
                    .build();
            countryMap.put(country.getCode(), country);
        }
        StopWatch watch = new StopWatch("PutRandomCountries");
        watch.start("putAllCountries");
        iMap.putAll(countryMap);
        watch.stop();
        log.info("Total Time = {} (milis)", watch.getTotalTimeMillis());
        log.info(watch.prettyPrint());
    }

    public void postRandomCountries2() {
        IMap<String, Country> iMap = hazelcastInstance.getMap("Country");
        StopWatch watch = new StopWatch("PutRandomCountries1By1");
        watch.start("putAllCountries1By1");
        for (int i = 0; i < MAX_LIMIT; i++) {
            Country country = Country.builder()
                    .code(String.valueOf(i) + "X")
                    .name("Countryzz " + i)
                    .continent("Asia")
                    .region("Southern and Central Asia")
                    .surfaceArea(BigDecimal.valueOf(99.99))
                    .indepYear((short) 2010)
                    .population(100 + i)
                    .lifeExpectancy(BigDecimal.valueOf(45.54))
                    .gnp(BigDecimal.valueOf(1000000))
                    .gnpOld(BigDecimal.valueOf(10000))
                    .localName("XYZ " + i)
                    .governmentform("GovXYZ " + i)
                    .headOfState("Head-XYZ " + i)
                    .capital(9999)
                    .code2("C" + i)
                    .build();
            iMap.put(country.getCode(), country);
        }
        watch.stop();
        log.info("Total Time = {} (milis)", watch.getTotalTimeMillis());
        log.info(watch.prettyPrint());
    }

    public void deleteAllCountries() {
        IMap<String, Country> iMap = hazelcastInstance.getMap("Country");
        iMap.clear();
    }
}
