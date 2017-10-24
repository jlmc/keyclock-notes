# Build
mvn clean package && docker build -t org.costajlmpp/demo-ldap .

# RUN

docker rm -f demo-ldap || true && docker run -d -p 8080:8080 -p 4848:4848 --name demo-ldap org.costajlmpp/demo-ldap 