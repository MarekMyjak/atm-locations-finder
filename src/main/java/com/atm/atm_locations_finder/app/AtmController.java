package com.atm.atm_locations_finder.app;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AtmController {

    private final AtmService atmService;

    @Autowired
    public AtmController(AtmService atmService) {
        this.atmService = atmService;
    }

    @ApiOperation(value = "Retrieve all HSBC ATMs locations within a circle defined by a center of the specified latitude and longitude (x,y) and radius (r)).")
    @GetMapping("atms-geographic-coordinates/latitudes/{latitude}/longitudes/{longitude}/radius/{radius}")
    public List<GeographicCoordinates> getGeographicCoordinates(@PathVariable double latitude,
                                                                @PathVariable double longitude,
                                                                @PathVariable double radius,
                                                                @ApiParam(defaultValue = "KILOMETERS") @RequestParam(required = false, defaultValue = "KILOMETERS") UnitType unit,
                                                                @ApiParam(defaultValue = "0",
                                                                        value = "This parameter is used to paginate response, with const limit of 20. At default return first 20 atms location.",
                                                                        allowableValues = "range[0, infinity]")
                                                                @RequestParam(required = false, defaultValue = "0") int page) {
        return atmService.getGeographicCoordinates(new GeographicCoordinates(latitude, longitude), radius, unit, page);
    }
}
