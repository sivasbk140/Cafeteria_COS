package net.breezeware.user.entity;

import net.breezeware.user.enumeration.Role;

public class User {

    private int id;
    private String name;
    private String password;
    private Role role;
    private String email;
    private String createdOn;
    private String updatedOn;

    public User() {
    }

    public User(int id, String name, String password,
                Role role, String email,
                String createdOn, String updatedOn) {
        this.id        = id;
        this.name      = name;
        this.password  = password;
        this.role      = role;
        this.email     = email;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                '}';
    }
}