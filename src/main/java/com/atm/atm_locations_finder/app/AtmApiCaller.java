package com.atm.atm_locations_finder.app;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

@Service
class AtmApiCaller {
    private static final String URL = "https://api.hsbc.com/open-banking/v2.2/atms";

    @SneakyThrows
    String callAtmApi() {
        HttpResponse<String> response = Unirest.get(URL)
                .header(ACCEPT, APPLICATION_JSON.getMimeType())
                .asString();
        if (HttpStatus.OK.value() == response.getStatus()) {
            return response.getBody();
        }
        throw new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE, String.format("Call to %s fail.", URL));
    }
}
