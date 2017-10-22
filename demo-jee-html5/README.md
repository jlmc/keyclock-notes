# Build
mvn clean package && docker build -t org.costajlmpp/demo-jee-html5 .

# RUN

docker rm -f demo-jee-html5 || true && docker run -d -p 8080:8080 -p 4848:4848 --name demo-jee-html5 org.costajlmpp/demo-jee-html5 