package org.costajlmpp.presentation;

/**
 * @author costa
 * on 24/10/2017.
 */
public class RedirectView {
    private final String viewName;

    public RedirectView(String viewName) {
        this.viewName = viewName;
    }

    @Override
    public String toString() {
        return this.viewName + "?faces-redirect=true";
    }

    public static RedirectView of (String viewName) {
        return new RedirectView(viewName);
    }
}
