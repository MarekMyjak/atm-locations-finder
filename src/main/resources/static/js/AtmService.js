'use strict';

angular.module('demo.services', []).factory('AtmService',
    ["$http", function ($http) {
        var service = {};
        service.getGeographicCoordinates = function (latitude, longitude, radius, unit) {
            var url = "atms-geographic-coordinates/latitudes/" + latitude + "/longitudes/" + longitude + "/radius/" + radius + "?unit=" + unit;
            return $http.get(url);
        };
        return service;
    }
    ]);