package com.atm.atm_locations_finder.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtmResponseMapper {
    private final ObjectMapper objectMapper;

    public AtmResponseMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    List<GeographicCoordinates> mapResponseToCoordinates(String response) {
        JsonNode rootNode = objectMapper.readTree(objectMapper.getFactory()
                .createParser(response));
        return rootNode.findValues("GeographicCoordinates").stream()
                .map(geographicCoordinate -> new GeographicCoordinates(
                        geographicCoordinate.get("Latitude").asDouble(),
                        geographicCoordinate.get("Longitude").asDouble()))
                .collect(Collectors.toList());
    }
}
