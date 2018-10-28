package com.atm.atm_locations_finder.app;

import org.junit.Test;

public class AtmApiCallerTest {

    private final AtmApiCaller atmApiCaller = new AtmApiCaller();

    @Test
    public void connectivityTest() {
        atmApiCaller.callAtmApi();
    }
}
