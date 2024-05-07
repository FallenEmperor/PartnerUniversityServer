package de.fhws.fiw.fds.sutton.server.api.security.model;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBModel;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.SuttonColumnConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class UserDB extends AbstractDBModel {

    @Column(name = SuttonColumnConstants.USER_NAME, unique = true)
    private String name;

    @Column(name = SuttonColumnConstants.USER_PASSWORD)
    private String password;

    @Column(name = SuttonColumnConstants.USER_ROLE)
    private String role;

    public UserDB() {
        // make JPA happy
    }

    public UserDB(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDB{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                "} " + super.toString();
    }
}
