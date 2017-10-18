# instalacao





Keyclock Server
-----------------------------------

O KEYCLOCK utiliza uma base de dados em memoria, para melhor entender esse schema vamos passar a usar uma database postgresSQl


```

curl -O https://downloads.jboss.org/keycloak/3.2.1.Final/keycloak-3.2.1.Final.zip && unzip keycloak-3.2.1.Final.zip

cd keycloak-3.2.1.Final/bin

./add-user.sh admin admin

curl --location --output $(pwd)/postgresql-42.0.0.jar --url https://jdbc.postgresql.org/download/postgresql-42.0.0.jar


./jboss-cli.sh -c

module add --name=org.postgresql --slot=main --resources=postgresql-42.0.0.jar --dependencies=javax.api,javax.transaction.api

/subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgresql",driver-class-name=org.postgresql.Driver)

data-source add --jndi-name=java:jboss/datasources/keyclockDemosDS --name=keyclockDemosDS --connection-url=jdbc:postgresql://localhost:5433/keyclock --driver-name=postgres --user-name=postgres --password=postgres

```

Como base de dados vamos usar um docker container
```
docker run --name costajlmpp-keyclock-postgres-db -p 5433:5432  -e POSTGRES_USER=postgres -e POSTGRES_DB=keyclock -e POSTGRES_PASSWORD=postgres -d postgres
```
--




The Database Configuration configuration for this component is found in the standalone.xml, standalone-ha.xml, or domain.xml file in your distribution. The location of this file depends on your operating mode.

Database Config
``` xml
<subsystem xmlns="urn:jboss:domain:keycloak-server:1.1">
    ...
    <spi name="connectionsJpa">
     <provider name="default" enabled="true">
         <properties>
             <property name="dataSource" value="java:jboss/datasources/KeycloakDS"/>
             <property name="initializeEmpty" value="false"/>
             <property name="migrationStrategy" value="manual"/>
             <property name="migrationExport" value="${jboss.home.dir}/keycloak-database-update.sql"/>
         </properties>
     </provider>
    </spi>
    ...
</subsystem>

```
As possiveis configurações são (http://www.keycloak.org/docs/latest/server_installation/topics/database/hibernate.html):

* dataSource  - JNDI name of the dataSource

* jta - boolean property to specify if datasource is JTA capable

* driverDialect - Value of database dialect. In most cases you don’t need to specify this property as dialect will be autodetected by Hibernate.

* initializeEmpty - Initialize database if empty. If set to false the database has to be manually initialized. If you want to manually initialize the database set migrationStrategy to manual which will create a file with SQL commands to initialize the database. Defaults to true.

* migrationStrategy - Strategy to use to migrate database. Valid values are update, manual and validate. Update will automatically migrate the database schema. Manual will export the required changes to a file with SQL commands that you can manually execute on the database. Validate will simply check if the database is up-to-date.

* migrationExport - Path for where to write manual database initialization/migration file.

* showSql - Specify whether Hibernate should show all SQL commands in the console (false by default). This is very verbose!

* formatSql - Specify whether Hibernate should format SQL commands (true by default)

* globalStatsInterval - Will log global statistics from Hibernate about executed DB queries and other things. Statistics are always reported to server log at specified interval (in seconds) and are cleared after each report.

* schema - Specify the database schema to us



Restart the server
$ .../bin/standalone.sh -Djboss.socket.binding.port-offset=100

http://localhost:8180/auth/



Wildfly
-----------------------------------


O wildfly onde estarão as applicações deve ter instalado o keyclock-connector (Client Adapter)
as instruções oficiais podem ser encontradas em: http://www.keycloak.org/docs/latest/getting_started/topics/secure-jboss-app/install-client-adapter.html


https://downloads.jboss.org/keycloak/3.2.1.Final/adapters/keycloak-oidc/keycloak-wildfly-adapter-dist-3.2.1.Final.zip


curl -O https://downloads.jboss.org/keycloak/3.2.1.Final/adapters/keycloak-oidc/keycloak-wildfly-adapter-dist-3.2.1.Final.zip && unzip keycloak-wildfly-adapter-dist-3.2.1.Final.zip

1. Unzip this file into the root directory of your Wildfly distribution.

2. ./jboss-cli.sh --file=adapter-install-offline.cli

3. $ .../bin/standalone.sh

Done !