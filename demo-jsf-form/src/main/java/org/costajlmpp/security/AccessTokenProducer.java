package org.costajlmpp.security;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.IDToken;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;


/**
 * https://www.n-k.de/2016/05/how-to-get-accesstoken-from-keycloak-springboot-javaee.html
 */
@RequestScoped
public class AccessTokenProducer {

    @Inject
    private HttpServletRequest request;

    @Produces
    public AccessToken getAccessToken() {
        return ((KeycloakPrincipal) request.getUserPrincipal()).getKeycloakSecurityContext().getToken();
    }

    @Produces
    @AuthUser
    public User decodeUser() {
        final Principal userPrincipal = request.getUserPrincipal();

        KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) userPrincipal;
        IDToken token = kp.getKeycloakSecurityContext().getIdToken();

        String jwtAcceassToken = kp.getKeycloakSecurityContext().getTokenString();

        Map<String, Object> otherClaims = token.getOtherClaims();

        String email = token.getEmail();
        String fullName = token.getName();
        String username = token.getPreferredUsername();
        String phone = (String) otherClaims.getOrDefault("phone", "");

        return User.Builder.builder()
                .name(fullName)
                .userName(username)
                .email(email)
                .phone(phone)
                .build();
    }


}

