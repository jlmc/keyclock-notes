# Keyclock database


directory: /Users/costa/Documents/trunk/keyclock-notes/docker-envirement/docker/keyclock-postgres-data-base
image Name: costajlmpp/keyclock-postgres-data-base
 
```
$ docker build -t costajlmpp/keyclock-postgres-data-base .
```

From the docker-envirement

```
$ docker build -t costajlmpp/keyclock-postgres-data-base docker/keyclock-postgres-data-base
``` 



docker run container - costajlmpp-keyclock-postgres-data-base

docker run -d -p 5442:5432 --name costajlmpp-keyclock-postgres-data-base costajlmpp/keyclock-postgres-data-base

# Keyclock

docker pull jboss/keycloak


-- docker run --name postgres -e POSTGRES_DB=keyclockdb -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -d postgres

    --link costajlmpp-keyclock-postgres-data-base:costajlmpp-keyclock-postgres-data-base \
     -e POSTGRES_DATABASE=keyclockdb -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres \
     
     
```
docker run --name costajlmpp-keycloak \
    --rm \
    --link costajlmpp-keyclock-postgres-data-base:costajlmpp-keyclock-postgres-data-base \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=postgres \
    -e POSTGRES_PORT_5432_TCP_ADDR=costajlmpp-keyclock-postgres-data-base \
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
docker run --name costajlmpp-keycloak \
    --rm \
    -e KEYCLOAK_USER=admin \
    -e KEYCLOAK_PASSWORD=admin \
    -p 8080:8080 \
    -p 9990:9990 \
    -p 443:443 \
     jboss/keycloak
```



jdbc:postgresql://localhost:5442/keyclockdb

ENV
POSTGRES_DATABASE=keyclockdb
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres

docker run --name mysql -e MYSQL_DATABASE=keycloak -e MYSQL_USER=keycloak -e MYSQL_PASSWORD=password -e MYSQL_ROOT_PASSWORD=root_password -d mysql


```
$ docker build -t costajlmpp/keyclock docker/keyclock
```

