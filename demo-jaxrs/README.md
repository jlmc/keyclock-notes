service-jee-jaxrs: JAX-RS Service
===================================================

Technologies: JavaEE
Summary: JAX-RS Service
Target Product: Keycloak, WildFly


What is it?
-----------

The `demo-jaxrs` quickstart demonstrates how to write a RESTful service with JAX-RS that is secured with Keycloak.

There are 3 endpoints exposed by the service:

* `demo-jaxrs/resourses/messages/public` - requires no authentication
* `demo-jaxrs/resourses/messages/secured` - can be invoked by users with the `user` role
* `demo-jaxrs/resourses/messages/admin` - can be invoked by users with the `admin` role

The endpoints are very simple and will only return a simple message stating what endpoint was invoked.


System Requirements
-------------------

You need to have WildFly 10 running.

All you need to build this project is Java 8.0 (Java SDK 1.8) or later and Maven 3.1.1 or later.


Configuration in Keycloak
-----------------------

Prior to running the quickstart you need to create a client in Keycloak and download the installation file.

The following steps shows how to create the client required for this quickstart:

* Open the Keycloak admin console
* Select `Clients` from the menu
* Click `Create`
* Add the following values:
  * Client ID: You choose (for example `demo-jaxrs`)
  * Client Protocol: `openid-connect`
* Click `Save`

Once saved you need to change the `Access Type` to `bearer-only` and click save.

Finally you need to configure the adapter, this is done by retrieving the adapter configuration file:

* Click on `Installation` in the tab for the client you created
* Select `Keycloak OIDC JSON`
* Click `Download`
* Move the file `keycloak.json` to the `config/` directory in the root of the quickstart

You may also want to enable CORS for the service if you want to allow invocations from HTML5 applications deployed to a
different host. To do this edit `keycloak.json` and add:

````
{
   ...
   "enable-cors": true
}
````


Run from Postman or Curl
-------------------------------

Obtain Token and Invoke Service

Keycloak allows you to make direct REST invocations to obtain an access token. To use it you must also have registered a valid Client to use as the "client_id" for this grant request.

1.So first we need to create a client that can be used to obtain the token. Go to the Keycloak admin console and create a new client. Give the Client ID a name and select public for access type.

Then in Settings, set "Direct Grants Only" on, and This switch is for clients that only use the Direct Access Grant protocol to obtain access tokens. Under Web Origins URIs enter you keycloak root (for example, https://auth.wpic-tools.com/auth)

As we are going to manually obtain a token and invoke the service let's increase the lifespan of tokens slightly. In production access tokens should have a relatively low timeout, ideally less than 5 minutes. To increase the timeout go to the Keycloak admin console again. This time click on Realm Settings then on Tokens. Change the value of Access Token Lifespan to 15 minutes. That should give us plenty of time to obtain a token and invoke the service before it expires.

In order to get a token, The REST URL to invoke on is /{keycloak-root}/realms/{realm-name}/protocol/openid-connect/token

Now we're ready to get our first token using CURL. To do this run:

RESULT=`curl --data "grant_type=password&client_id=curl&username=yourusername&password=*****" https://auth.wpic-tools.com/auth/realms/wpic/protocol/openid-connect/token`
Basically what we are doing here is invoking Keycloaks OpenID Connect token endpoint with grant type set to password which is the Resource Owner Credentials flow that allows swapping a username and a password for a token. Take a look at the result by running: echo $RESULT

For public client's, the POST invocation requires form parameters that contain the username, credentials, and client_id of your application. For example:

POST /auth/realms/demo/protocol/openid-connect/token
Content-Type: application/x-www-form-urlencoded

username=bburke&password=geheim&client_id=curl&grant_type=password
2.The result is a JSON document that contains a number of properties. There's only one we need for now though so we need to parse this output to retrieve only the value we want. To do this run:

TOKEN=`echo $RESULT | sed 's/.*access_token":"//g' | sed 's/".*//g'`
This command uses sed to strip out everything before and after the value of the access token property.

3.Now that we have the token we can invoke the secured service. To do this run:

curl https://squirrel-staging.wpic-tools.com/rest/tasks -H "Authorization: bearer $TOKEN"

----
1. get token

RESULT=`curl -v --data "grant_type=password&client_id=curl&username=costa&password=*****" http://localhost:8180/auth/realms/curl/protocol/openid-connect/token`


RESULT=`curl -v --data "grant_type=password&client_id=curl&username=<Your username>&password=<your password>" http://localhost:8180/auth/realms/realm_of_demos/protocol/openid-connect/token`

TOKEN=`echo $RESULT | sed 's/.*access_token":"//g' | sed 's/".*//g'`


curl http://localhost:8080/demo-jaxrs/resources/messages/public -H "Authorization: bearer $TOKEN"
curl http://localhost:8080/demo-jaxrs/resources/messages/monitor -H "Authorization: bearer $TOKEN"
curl http://localhost:8080/demo-jaxrs/resources/messages/admin -H "Authorization: bearer $TOKEN"






curl -X POST \
  http://localhost:8180/auth/demo-jsf-form/protocol/openid-connect/token \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/x-www-form-urlencoded' \
  -H 'postman-token: 799f7a7c-76c2-1ed0-5a05-a21b33c26e8a' \
  -d 'username=costa&password=password&client_id=CURL_app&grant_type=client_credentials'
  
  
http://localhost:8180/auth/realms/demo-jaxrs/protocol/openid-connect/token


POST http://localhost:8180/auth/realms/demo-jaxrs/protocol/openid-connect/token

client_id : demo-jaxrs
grant_type : client_credentials
client_secret: 

x-wwww-form-urlencoder

http://planet.jboss.org/post/getting_started_with_keycloak_securing_a_rest_service

Import users from ldap
-----
http://www.keycloak.org/docs/2.5/server_admin/topics/user-federation/ldap.html

Build and Deploy the Quickstart
-------------------------------

1. Open a terminal and navigate to the root directory of this quickstart.

2. The following shows the command to deploy the quickstart:

   ````
   mvn install wildfly:deploy
   ````

If you prefer to secure WARs via Keycloak subsystem:

   ````
   mvn install -Dsubsystem wildfly:deploy
   ````

Access the Quickstart
---------------------

The endpoints for the service are:

* public - <http://localhost:8080/service/public>
* secured - <http://localhost:8080/service/secured>
* admin - <http://localhost:8080/service/admin>

You can open the public endpoint directly in the browser to test the service. The two other endpoints require
invoking with a bearer token. To invoke these endpoints use one of the example quickstarts:

* [app-jee-html5](../app-jee-html5/README.md) - HTML5 application that invokes the example service. Requires service example to be deployed.
* [app-jee-jsp](../app-jee-jsp/README.md) - JSP application packaged that invokes the example service. Requires service example to be deployed.

Integration test of the Quickstart
----------------------------------

1. Make sure you have an Keycloak server running with an admin user in the `master` realm or use the provided docker image
2. Be sure to set the `TestHelper.keycloakBaseUrl` in the `createArchive` method.
3. Run `mvn clean install -Pwildfly-managed`


Undeploy the Quickstart
-----------------------

1. Open a terminal and navigate to the root directory of this quickstart.

2. The following shows the command to undeploy the quickstart:

   ````
   mvn install wildfly:undeploy
   ````
