package com.atm.atm_locations_finder.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AtmResponseMapperTest {

    private final AtmResponseMapper atmResponseMapper = new AtmResponseMapper(new ObjectMapper());

    @Test
    public void mapperTest() throws IOException {
        URL url = Resources.getResource("atms-data.json");
        String atmsData = Resources.toString(url, Charsets.UTF_8);

        List<GeographicCoordinates> geographicCoordinates = atmResponseMapper.mapResponseToCoordinates(atmsData);

        assertEquals(1612, geographicCoordinates.size());
    }
}
