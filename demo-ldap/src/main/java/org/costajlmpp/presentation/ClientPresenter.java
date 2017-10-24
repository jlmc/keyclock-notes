package org.costajlmpp.presentation;

import org.costajlmpp.security.AuthUser;
import org.costajlmpp.security.Role;
import org.costajlmpp.security.User;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Model
public class ClientPresenter {
    @Inject
    Principal principal;

    @Inject
    HttpServletRequest request;

    @Inject @AuthUser
    User user;

    public String getPrincipal() {
        return principal.getName();
    }

    public User getUser() {
        return user;
    }

    public List<String> getRoles() {
        final List<String> roles = Stream.of(Role.values()).map(this::role)
                .flatMap(o -> o.map(Stream::of).orElse(Stream.empty()))
                .collect(Collectors.toList());

        return roles;
    }

    private Optional<String> role (Role role) {
        if (request.isUserInRole(role.toString())) {
            return Optional.of(role.toString());
        }
        return Optional.empty();
    }

    public RedirectView logout() {
        try {
            request.logout();

            return RedirectView.of("/index");
        } catch (Exception e) {
            final FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().getFlash().setKeepMessages(true);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,null, e.getMessage()));
            return RedirectView.of("/index");
        }
    }

}
