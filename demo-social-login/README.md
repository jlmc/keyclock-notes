# Social Login

* https://keycloak.gitbooks.io/documentation/server_admin/topics/identity-broker/social-login.html


# Build
mvn clean package && docker build -t org.costajlmpp/demo-social-login .

# RUN

docker rm -f demo-social-login || true && docker run -d -p 8080:8080 -p 4848:4848 --name demo-social-login org.costajlmpp/demo-social-login 