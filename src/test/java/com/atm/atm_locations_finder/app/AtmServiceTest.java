package com.atm.atm_locations_finder.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AtmServiceTest {

    private AtmService atmService;

    @Before
    public void setUp() throws IOException {
        URL url = Resources.getResource("atms-data.json");
        String atmsData = Resources.toString(url, Charsets.UTF_8);
        AtmApiCaller atmApiCaller = mock(AtmApiCaller.class);
        when(atmApiCaller.callAtmApi()).thenReturn(atmsData);
        atmService = new AtmService(new UnitTypeTransformer(), atmApiCaller, new AtmResponseMapper(new ObjectMapper()), new AtmResponseFilter());
    }

    @Test
    public void oneAtmTest() {
        List<GeographicCoordinates> geographicCoordinates = atmService.getGeographicCoordinates(new GeographicCoordinates(52, -1), 16, UnitType.KILOMETERS, 0);
        assertEquals(1, geographicCoordinates.size());
        assertEquals(51.8971868, geographicCoordinates.get(0).getLatitude(), 0.01);
        assertEquals(-1.1505458, geographicCoordinates.get(0).getLongitude(), 0.01);
    }

    @Test
    public void firstPageTest() {
        List<GeographicCoordinates> geographicCoordinates = atmService.getGeographicCoordinates(new GeographicCoordinates(52, -1), 50, UnitType.KILOMETERS, 0);
        assertEquals(20, geographicCoordinates.size());
    }

    @Test
    public void secondPageTest() {
        List<GeographicCoordinates> geographicCoordinates = atmService.getGeographicCoordinates(new GeographicCoordinates(52, -1), 50, UnitType.KILOMETERS, 1);
        assertEquals(10, geographicCoordinates.size());
    }

    @Test
    public void noAtmInThatAreaTest() {
        List<GeographicCoordinates> geographicCoordinates = atmService.getGeographicCoordinates(new GeographicCoordinates(0, 0), 1, UnitType.KILOMETERS, 0);
        assertEquals(0, geographicCoordinates.size());
    }
}
