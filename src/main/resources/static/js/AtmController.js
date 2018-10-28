'use strict';

var module = angular.module('demo.controllers', []);
module.controller("AtmController", ["$scope", "AtmService",
    function ($scope, AtmService) {
        var map = new L.Map('map');
        $scope.coordinates = {
            latitude: null,
            longitudes: null
        };
        $scope.radius = null;
        $scope.unit = "KILOMETERS";

        $scope.noResults = false;
        $scope.error = false;

        $scope.updateMap = function () {
            $scope.noResults = false;
            $scope.error = false;
            console.log("hello", $scope);
            AtmService.getGeographicCoordinates(
                $scope.coordinates.latitude,
                $scope.coordinates.longitudes,
                $scope.radius,
                $scope.unit).then(function (response) {
                    var markers = response.data;

                    if (markers.length === 0){
                        $scope.noResults = true;
                        return;
                    }

                    console.log(markers);
                    map.eachLayer(function (layer) {
                        map.removeLayer(layer);
                    });

                    L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                        attribution: '&copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors',
                        maxZoom: 18
                    }).addTo(map);
                    map.attributionControl.setPrefix(''); // Don't show the 'Powered by Leaflet' text.

                    var center = new L.LatLng($scope.coordinates.latitude, $scope.coordinates.longitudes);
                    console.log($scope);
                    map.setView(center, 9);
                    for (var i = 0; i < markers.length; i++) {
                        var lon = markers[i]["Longitude"];
                        var lat = markers[i]["Latitude"];

                        var markerLocation = new L.LatLng(lat, lon);
                        var marker = new L.Marker(markerLocation);
                        map.addLayer(marker);
                    }
                },
                function (reason) {
                    $scope.error = true;
                    console.log("error occurred", reason)
                })
        };
    }]);