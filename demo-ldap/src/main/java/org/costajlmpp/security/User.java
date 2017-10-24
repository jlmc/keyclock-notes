package org.costajlmpp.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class User {

    private String userName;
    private String name;
    private String email;
    private String phone;
    private String address;


    private Set<String> roles = new HashSet<>();

    private User (){}

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Set<String> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    static Builder builder() {
        return new Builder();
    }

    static class Builder {
        private String userName;
        private String name;
        private String email;
        private String phone;
        private String address;
        private Set<String> roles = new HashSet<>();

        private Builder() {}

        public User build() {
            final User user = new User();

            user.name = this.name;
            user.userName = this.userName;
            user.email = this.email;
            user.phone = this.phone;
            user.address = this.address;
            user.roles.addAll(this.roles);

            return user;
        }

        public Builder address (String address) {
            this.address = address;
            return this;
        }

        public Builder phone (String phone) {
            this.phone = phone;
            return this;
        }

        public Builder email (String email) {
            this.email = email;
            return this;
        }

        public Builder name (String name) {
            this.name = name;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder addRole(String... roles) {
            this.roles.addAll(Arrays.asList(roles));
            return this;
        }

    }

}
