package com.atm.atm_locations_finder.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
class GeographicCoordinates{
    @JsonProperty("Latitude")
    private double latitude;
    @JsonProperty("Longitude")
    private double longitude;

    /**
     * This method calculate distance in Kilometers to given coordinates.
     *
     * @return distance in Kilometers
     */
    double distanceTo(@NonNull GeographicCoordinates coordinates) {
        final int radiusOfTheEarth = 6371;
        double latDistance = Math.toRadians(coordinates.getLatitude() - latitude);
        double lonDistance = Math.toRadians(coordinates.getLongitude() - longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(coordinates.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radiusOfTheEarth * c;
    }
}
