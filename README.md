# atm-locations-finder


Check this app online [atm-locations-finder](https://atm-locations-finder.herokuapp.com/swagger-ui.html#/)

This application expose REST service accepting point (circle center) and radius as parameters, then retrieve all HSBC ATMs locations within a circle defined by a center of the specified latitude and longitude (x,y) and radius (r). 
Specification can be found on swagger ui(see below).

### Swagger

Swagger endpoint is automatically created during application start, and can be accessed  here:
```
{app.url}/swagger-ui.html
e.g. http://localhost:8080/swagger-ui.html
```

### Views

This app contains simple UI under resource.

```
/home
e.g. http://localhost:8080/home
```

### Running

It's standard spring boots application with maven. 

To build execute:
```
mvn clean package
```
To run execute:
```
java -jar target/atm-locations-finder-0.0.1-SNAPSHOT.jar
```

You can also run this application from IDE, e.g. IntelliJ IDEA

### Examples of use

This app is actual deployed to heroku free instance. So you can just click one of below links

Swagger:
- https://atm-locations-finder.herokuapp.com/swagger-ui.html#/

Atms-locations:
- https://atm-locations-finder.herokuapp.com/atms-geographic-coordinates/latitudes/52/longitudes/-1/radius/44?page=1&unit=MILES
- https://atm-locations-finder.herokuapp.com/atms-geographic-coordinates/latitudes/52/longitudes/-1/radius/20?page=0&unit=KILOMETERS

UI:
- https://atm-locations-finder.herokuapp.com/home
