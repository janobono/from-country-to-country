# from-country-to-country

Simple Spring Boot service, that is able to calculate any possible land route from one country to another.

## requirements

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

## environment variables

|Name| Default value                                                             | Description |
|---|---------------------------------------------------------------------------|-------------|
|APP_DATA_LINK|https://raw.githubusercontent.com/mledoze/countries/master/countries.json| Data URL    |
