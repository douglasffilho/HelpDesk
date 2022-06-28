package br.com.daniel.domain;

import java.util.Date;

public class User extends Entity {
    private String name;
    private String email;
    private String password;

    public User(final String id, final Date createdAt, final String createdBy) {
        super(id, createdAt, createdBy);
    }

    public User(final String createdBy) {
        super(createdBy);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        return this.email.equals(user.email);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + this.email.hashCode();
        return result;
    }
}
