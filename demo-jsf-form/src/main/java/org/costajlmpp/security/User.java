package org.costajlmpp.security;

public class User {

    private String userName;
    private String name;
    private String email;
    private String phone;
    private String address;

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

    static class Builder {
        private String userName;
        private String name;
        private String email;
        private String phone;
        private String address;

        private Builder() {}

        static Builder builder() {
            return new Builder();
        }

        public User build() {
            final User user = new User();

            user.name = this.name;
            user.userName = this.userName;
            user.email = this.email;
            user.phone = this.phone;
            user.address = this.address;

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

    }

}
