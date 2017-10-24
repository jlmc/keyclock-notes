package org.costajlmpp.presentation;

import org.costajlmpp.security.AuthUser;
import org.costajlmpp.security.User;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Set;

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

    public Set<String> getRoles() {

        return user.getRoles();
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
