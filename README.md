# atm-locations-finder

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