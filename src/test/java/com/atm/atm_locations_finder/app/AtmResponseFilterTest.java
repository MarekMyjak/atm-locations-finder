package com.atm.atm_locations_finder.app;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AtmResponseFilterTest {

    private final AtmResponseFilter atmResponseFilter = new AtmResponseFilter();

    @Test
    public void filterTest() {
        GeographicCoordinates center = new GeographicCoordinates(0, 0);
        GeographicCoordinates g1 = new GeographicCoordinates(0, 0.3);
        GeographicCoordinates g2 = new GeographicCoordinates(0.1, -0.2);
        List<GeographicCoordinates> geographicCoordinates = Arrays.asList(
                g1,
                g2,
                new GeographicCoordinates(0, 10),
                new GeographicCoordinates(10, 1));
        List<GeographicCoordinates> result = atmResponseFilter.filterAndLimit(geographicCoordinates, center, 40, 0);

        assertEquals(2, result.size());
        assertTrue(result.contains(g1));
        assertTrue(result.contains(g2));
    }

    @Test
    public void limitTest() {
        List<GeographicCoordinates> result = getListForPage(0);

        assertEquals(20, result.size());
    }

    @Test
    public void nextPageTest() {
        List<GeographicCoordinates> result = getListForPage(1);

        assertEquals(5, result.size());
    }

    private List<GeographicCoordinates> getListForPage(int page) {
        Random r = new Random();
        List<GeographicCoordinates> geographicCoordinates = new LinkedList<>();
        for (int i = 0; i < 25; i++) {
            geographicCoordinates.add(new GeographicCoordinates(0.05 * r.nextDouble(), 0.05 * r.nextDouble()));
        }
        GeographicCoordinates center = new GeographicCoordinates(0, 0);
        return atmResponseFilter.filterAndLimit(geographicCoordinates, center, 40, page);
    }
}
