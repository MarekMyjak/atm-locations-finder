package com.atm.atm_locations_finder.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class AtmService {
    private final UnitTypeTransformer unitTypeTransformer;
    private final AtmApiCaller atmApiCaller;
    private final AtmResponseMapper atmResponseMapper;
    private final AtmResponseFilter atmResponseFilter;

    @Autowired
    public AtmService(UnitTypeTransformer unitTypeTransformer, AtmApiCaller atmApiCaller, AtmResponseMapper atmResponseMapper, AtmResponseFilter atmResponseFilter) {
        this.unitTypeTransformer = unitTypeTransformer;
        this.atmApiCaller = atmApiCaller;
        this.atmResponseMapper = atmResponseMapper;
        this.atmResponseFilter = atmResponseFilter;
    }

    List<GeographicCoordinates> getGeographicCoordinates(GeographicCoordinates center, double radius, UnitType unit, int page) {
        double radiusInKilometers = unitTypeTransformer.toKilometers(radius, unit);
        String responseAsString = atmApiCaller.callAtmApi();
        List<GeographicCoordinates> geographicCoordinates = atmResponseMapper.mapResponseToCoordinates(responseAsString);
        return atmResponseFilter.filterAndLimit(geographicCoordinates, center, radiusInKilometers, page);
    }
}
