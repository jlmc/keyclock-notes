# Links uteis

http://localhost:8180/auth/

Apos o user criado 
http://localhost:8180/auth/realms/realm_name/account


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
 -DarchetypeGroupId=org.costajlmpp \
 -DarchetypeArtifactId=javaee8-essentials-archetype \
 -DarchetypeVersion=0.0.1 \
 -DgroupId=org.costajlmpp \
 -DartifactId=demo-jsf-form