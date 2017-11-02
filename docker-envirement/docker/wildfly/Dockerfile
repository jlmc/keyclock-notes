FROM jboss/wildfly:10.1.0.Final

# Env vars are expected for DB Details, defaults are only for local envs
#ENV DB_URL jdbc:postgresql://postgresql-rockall:5432/fastnet
#ENV DB_TEST_URL jdbc:postgresql://postgresql-rockall-test:5432/fastnet_test
#ENV DB_USER postgres
#ENV DB_PASSWORD postgres
ENV KEYCLOAK_VERSION 3.3.0.Final






#COPY docker/wildfly/local.conf /etc/fonts/
#ADD docker/wildfly/config_wildfly.sh /opt/jboss/wildfly/customization/
#ADD docker/wildfly/commands.cli /opt/jboss/wildfly/customization/

ADD config_wildfly.sh /opt/jboss/wildfly/customization/
ADD commands.cli /opt/jboss/wildfly/customization/

# VOLUME opt/jboss/wildfly/customization


# execute the customization commands
RUN ["/opt/jboss/wildfly/customization/config_wildfly.sh"]
RUN rm -rf  /opt/jboss/wildfly/standalone/configuration/standalone_xml_history

WORKDIR /opt/jboss/wildfly
RUN curl -L https://downloads.jboss.org/keycloak/$KEYCLOAK_VERSION/adapters/keycloak-oidc/keycloak-wildfly-adapter-dist-$KEYCLOAK_VERSION.tar.gz | tar zx
RUN curl -L https://downloads.jboss.org/keycloak/$KEYCLOAK_VERSION/adapters/saml/keycloak-saml-wildfly-adapter-dist-$KEYCLOAK_VERSION.tar.gz | tar zx
WORKDIR /opt/jboss

# Standalone.xml modifications.
RUN sed -i -e 's/<extensions>/&\n        <extension module="org.keycloak.keycloak-adapter-subsystem"\/>/' $JBOSS_HOME/standalone/configuration/standalone.xml && \
    sed -i -e 's/<profile>/&\n        <subsystem xmlns="urn:jboss:domain:keycloak:1.1"\/>/' $JBOSS_HOME/standalone/configuration/standalone.xml && \
    sed -i -e 's/<security-domains>/&\n                <security-domain name="keycloak">\n                    <authentication>\n                        <login-module code="org.keycloak.adapters.jboss.KeycloakLoginModule" flag="required"\/>\n                    <\/authentication>\n                <\/security-domain>/' $JBOSS_HOME/standalone/configuration/standalone.xml && \
    sed -i -e 's/<extensions>/&\n        <extension module="org.keycloak.keycloak-saml-adapter-subsystem"\/>/' $JBOSS_HOME/standalone/configuration/standalone.xml && \
    sed -i -e 's/<profile>/&\n        <subsystem xmlns="urn:jboss:domain:keycloak-saml:1.1"\/>/' $JBOSS_HOME/standalone/configuration/standalone.xml



CMD ["/opt/jboss/wildfly/bin/standalone.sh", "--debug", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
EXPOSE 8787