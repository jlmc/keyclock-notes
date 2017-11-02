# Keyclock database


directory: /Users/costa/Documents/trunk/keyclock-notes/docker-envirement/docker/keyclock-postgres-data-base
image Name: ey/keyclock-postgres-data-base
 
```
$ docker build -t ey/keyclock-postgres-data-base .
```

From the docker-envirement

```
$ docker build -t ey/keyclock-postgres-data-base docker/keyclock-postgres-data-base
``` 



docker run container - ey-keyclock-postgres-data-base

docker run -d -p 5442:5432 --name ey-keyclock-postgres-data-base ey/keyclock-postgres-data-base

# Keyclock

docker pull jboss/keycloak

     
```
docker run --name ey-keycloak \
    --rm \
    --link ey-keyclock-postgres-data-base:ey-keyclock-postgres-data-base \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=postgres \
    -e POSTGRES_PORT_5432_TCP_ADDR=ey-keyclock-postgres-data-base \
    -e POSTGRES_DATABASE=keyclockdb \
    -e POSTGRES_PORT_5432_TCP_PORT=5432 \
    -e KEYCLOAK_USER=admin \
    -e KEYCLOAK_PASSWORD=admin \
    -p 8080:8080 \
    -p 9990:9990 \
    -p 443:443 \
     jboss/keycloak
```     
     
     
```
docker run --name ey-keycloak \
    --rm \
    -e KEYCLOAK_USER=admin \
    -e KEYCLOAK_PASSWORD=admin \
    -p 8080:8080 \
    -p 9990:9990 \
    -p 443:443 \
     jboss/keycloak
```


# Wildfly

```
$ docker build -t ey/wildfly .
```


```
docker run --name ey-wildfly \
    --rm \
    -p 8080:8080 \
    -p 9990:9990 \
    -p 443:443 \
    ey/wildfly
    


```
docker exec -it ey-wildfly /bin/bash