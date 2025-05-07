
### REST

#### Endpoints

web app port configuration:

1. Docker file:

```
ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Dquarkus.http.port=${PORT}"
```

2. Application properties

```
quarkus.http.port=${PORT:8080}
```

```
quarkus.http.root-path=/api
```

#### Health endpoint

Add the SmallRye Health extension:

```
    implementation 'io.quarkus:quarkus-smallrye-health'
```

Once added, the following endpoints become available automatically:

* GET /q/health → General health
* GET /q/health/live → Liveness check
* GET /q/health/ready → Readiness check

```
### BASE Configuration: General health
GET http://localhost:8080/q/health

### BASE Configuration: Liveness check
GET http://localhost:8080/q/health/live

### BASE Configuration: Readiness check
GET http://localhost:8080q/health/ready
```

Customization:

By default, all Quarkus system endpoints are under /q/. To change the health check path:

```
quarkus.http.root-path=/api
quarkus.smallrye-health.root-path=/health
```
