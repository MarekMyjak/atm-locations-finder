package com.atm.atm_locations_finder.app;

import org.springframework.stereotype.Service;

@Service
class UnitTypeTransformer {

    private static final double MILES_TO_KILOMETERS = 1.609344;

    public double toKilometers(double radius, UnitType unit) {
        if (unit == UnitType.MILES) {
            return radius * MILES_TO_KILOMETERS;
        }
        return radius;
    }
}
