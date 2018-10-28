package com.atm.atm_locations_finder.app;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AtmResponseFilter {
    private static final int LIMIT_OF_RETURNED_ATM = 20;

    List<GeographicCoordinates> filterAndLimit(List<GeographicCoordinates> geographicCoordinates,
                                               GeographicCoordinates center,
                                               double radius,
                                               int page) {
        Stream<GeographicCoordinates> filteredStream = filterInRadius(geographicCoordinates.stream(), center, radius);
        return limitElements(filteredStream, center, page).collect(Collectors.toList());
    }

    private Stream<GeographicCoordinates> filterInRadius(Stream<GeographicCoordinates> geographicCoordinatesStream,
                                                         GeographicCoordinates center,
                                                         double radius) {
        return geographicCoordinatesStream
                .filter(geographicCoordinate -> geographicCoordinate.distanceTo(center) < radius)
                .distinct();
    }

    private Stream<GeographicCoordinates> limitElements(Stream<GeographicCoordinates> geographicCoordinatesStream,
                                                        GeographicCoordinates center,
                                                        int page) {
        return geographicCoordinatesStream
                .sorted(Comparator.comparingDouble(o -> o.distanceTo(center)))
                .skip(page * LIMIT_OF_RETURNED_ATM)
                .limit(LIMIT_OF_RETURNED_ATM);
    }
}
