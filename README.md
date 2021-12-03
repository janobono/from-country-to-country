# from-country-to-country

## Assignment

Your task is to create a simple Spring Boot service, that is able to calculate any possible land route from one country
to another. The objective is to take a list of country data in JSON format and calculate the route by utilizing
individual countries border information.

### Specification

- Spring Boot, Maven
- Data link: `https://raw.githubusercontent.com/mledoze/countries/master/countries.json`
- The application exposes REST endpoint `/routing/{origin}/{destination}` that returns a list of border crossings to get
  from origin to destination
- Single route is returned if the journey is possible
- Algorithm needs to be efficient
- If there is no land crossing, the endpoint returns `HTTP 400`
- Countries are identified by `cca3` field in country data
- HTTP request sample (land route from Czech Republic to Italy):

```
GET /routing/CZE/ITA HTTP/1.0:
{
"route" : ["CZE", "AUT", "ITA"]
}
```

### Expected deliveries:

1. Source code
2. Instructions on how to build and run the application

## Realisation

### requirements

- [jdk17](https://adoptium.net/)
- [maven 3.8.3](https://maven.apache.org/)
- [docker](https://docs.docker.com/get-docker/)
- [docker-compose](https://docs.docker.com/compose/) - optional for local run

## build

```
mvn clean install
```

## local run

Docker compose for local run is preconfigured in `from-country-to-country` subdirectory.

### start

```
docker-compose up
```

### stop

```
docker-compose down
```

## endpoints

| URL                              | Description                  |
|----------------------------------|------------------------------|
| /health                          | Simple check health endpoint |
| /routing/{origin}/{destination}  | Calculate route endpoint     |

## configuration variables

|Name| Default value                                                             | Description |
|---|---------------------------------------------------------------------------|-------------|
|APP_DATA_LINK|https://raw.githubusercontent.com/mledoze/countries/master/countries.json| Data URL    |

## example

### CZE - ITA

call:
```
curl -i -X GET -w "\n" localhost/routing/CZE/ITA
```
result:
```
HTTP/1.1 200 OK
Content-Type: application/json
Date: Fri, 03 Dec 2021 07:11:03 GMT
Content-Length: 29

{"route":["CZE","AUT","ITA"]}
```

### Unreal origin and destination

call:
```
curl -i -X GET -w "\n" localhost/routing/somewhere/nowhere
```

result:
```
HTTP/1.1 400 Bad Request
Content-Type: application/json
Date: Fri, 03 Dec 2021 07:13:02 GMT
Content-Length: 116

{"timestamp":"2021-12-03T07:13:02.264+00:00","status":400,"error":"Bad Request","path":"/routing/somewhere/nowhere"}
```
