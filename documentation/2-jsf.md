# Links uteis

http://localhost:8180/auth/

Apos o user criado 
http://localhost:8180/auth/realms/realm_name/account
http://localhost:8180/auth/realms/realm_of_demos/account


# LOGOUT
Logout

You can log out of a web application in multiple ways. For Java EE servlet containers, you can call HttpServletRequest.logout(). For other browser applications, you can redirect the browser to http://auth-server/auth/realms/{realm-name}/protocol/openid-connect/logout?redirect_uri=encodedRedirectUri, which logs you out if you have an SSO session with your browser.

# Add More Info to the Payload
REALM > Clients > MyClient > Settings::Access Type (public) -> Bearer-only

Add claim mapping:

1. Open the admin console of your realm.
2. Go to Clients and open your client
3. This only works for Settings > Access Type confidential or public (not bearer-only)
4. Go to Mappers
5. Create a mapping from your attribute to json
6. Check "Add to ID token"

#JSF

mvn archetype:generate \
 -DarchetypeGroupId=org.jcosta \
 -DarchetypeArtifactId=javaee8-essentials-archetype \
 -DarchetypeVersion=0.0.1 \
 -DgroupId=org.costajlmpp \
 -DartifactId=demo-jaxrs